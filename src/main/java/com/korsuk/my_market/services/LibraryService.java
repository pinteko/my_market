package com.korsuk.my_market.services;

import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;
import com.korsuk.my_market.repo.LibraryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;

@Service
public class LibraryService {

        private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Novel> getReadNovels(Student student) {
            return libraryRepository.getReadNovels(student);
        }


        public List<Student> getStudentsWhoRead(Novel novel)  {
            return libraryRepository.getStudentsWhoRead(novel);
        }

}
