package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.AuthorDto;
import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.exceptions.ExistEntityException;
import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.repo.NovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;

    public List<NovelDto> getAllNovels() {
       List<Novel> novels = novelRepository.findAll();
       List<NovelDto> novelsDto = novels.stream().map(NovelDto::new).collect(Collectors.toList());
       return novelsDto;
    }

    public NovelDto getNovelByIdDto(Long id) {
       Novel novel = novelRepository.findNovelById(id);
       if (novel != null) {
       NovelDto novelDto = new NovelDto(novel);
       return novelDto;}
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
