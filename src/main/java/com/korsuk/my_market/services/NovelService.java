package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.exceptions.ExistEntityException;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.repo.NovelRepository;
import com.korsuk.my_market.repo.specification.NovelSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;

    public List<NovelDto> getAllNovels() {
       List<Novel> novels = novelRepository.findAll();
       return novels.stream().map(NovelDto::new).collect(Collectors.toList());
    }

    public Page<NovelDto> findNovels(Integer p, Double minRating, Double maxRating,
                                  Double minPrice, Double maxPrice, String titlePart, String authorNamePart) {
        Specification<Novel> spec = Specification.where(null);
        if (minRating != null) {
            spec = spec.and(NovelSpecification.ratingGreaterThanOrEqualTo(minRating));
        }
        if (maxRating != null) {
            spec = spec.and(NovelSpecification.ratingLessThanOrEqualTo(maxRating));
        }
        if (minPrice != null) {
            spec = spec.and(NovelSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(NovelSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(NovelSpecification.titleLike(titlePart));
        }
        if (authorNamePart != null) {
            spec = spec.and(NovelSpecification.authorNameLike(authorNamePart));
        }

        spec = spec.and(NovelSpecification.orderById());

       Page<Novel> pageNovels = novelRepository.findAll(spec, PageRequest.of(p - 1, 5));

        return pageNovels.map(this::convertToNovelDto);
    }

    private NovelDto convertToNovelDto(Novel o) {
        return new NovelDto(o);
    }

    public NovelDto getNovelByIdDto(Long id) {
       Novel novel = novelRepository.findNovelById(id);
       if (novel != null) {
       return new NovelDto(novel);
       }
       return null;
    }

    public Novel getNovelById(Long id) {
        return novelRepository.findNovelById(id);
    }

    public void deleteBookById(Long id) {
        novelRepository.deleteById(id);
    }

    public void changeRating(Long id, Double delta) {
        Novel novel = novelRepository.findNovelById(id);
        novel.setRating(novel.getRating() + delta);
        novelRepository.save(novel);
    }

    public NovelDto save(Novel novel) {
        if (novelRepository.existsNovelByTitle(novel.getTitle())) {
            throw new ExistEntityException("This novel is already in library");
        } else {
            Novel novelSaved = novelRepository.saveAndFlush(novel);
            return new NovelDto(novelSaved);
        }
    }
}
