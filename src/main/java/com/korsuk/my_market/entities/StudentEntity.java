package com.korsuk.my_market.entities;

import com.korsuk.my_market.products.Novel;
import lombok.Data; //for Getter and Setter

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@Data
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToMany
    @JoinTable(name = "novel_readers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id"))
    private List<Novel> novels;


}
