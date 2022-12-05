package com.korsuk.my_market.services;

import com.korsuk.my_market.entities.GroupEntity;
import com.korsuk.my_market.repo.GroupRepository;
import com.korsuk.my_market.soap.groups.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public static final Function<GroupEntity, Group> functionEntityToSoap = ge -> {
        Group g = new Group();
        g.setTitle(g.getTitle());
        ge.getStudentEntities().stream().map(StudentService.functionEntityToSoap).forEach(s -> g.getStudents().add(s));
        return g;
    };

    public Group getByTitle(String title) {
        return groupRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}
