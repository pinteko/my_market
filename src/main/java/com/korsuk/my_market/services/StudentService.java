package com.korsuk.my_market.services;

import com.korsuk.my_market.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Object getStudent(int id) {
        return null;
    }
}
