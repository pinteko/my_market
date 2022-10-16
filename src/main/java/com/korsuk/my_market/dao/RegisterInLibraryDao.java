package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;

import java.util.List;

public interface RegisterInLibraryDao {

    void readNovels(Long id_student, Long id_novel);

}
