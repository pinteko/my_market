package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.AuthorDto;
import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.repo.NovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;

    public List<NovelDto> getAllNovels() {
       List<Novel> novels = novelRepository.findAll();
       List<NovelDto> novelsDto = novels.stream().map(n -> new NovelDto(n.getId(),
               n.getTitle(), new AuthorDto(n.getAuthor().getId(),
               n.getAuthor().getName(), n.getAuthor().getSurname()), n.getRating(), n.getPrice())).collect(Collectors.toList());
       return novelsDto;
    }

    public NovelDto getNovelByIdDto(Long id) {
       Novel novel = novelRepository.findNovelById(id);
       NovelDto novelDto = new NovelDto(novel.getId(), novel.getTitle(),
               new AuthorDto(novel.getAuthor().getId(), novel.getAuthor().getName(), novel.getAuthor().getSurname()),
               novel.getRating(), novel.getPrice());
       return novelDto;
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
        novelRepository.saveAndFlush(novel);
    }

    public Novel save(Novel novel) {
        return novelRepository.saveAndFlush(novel);
    }
}
