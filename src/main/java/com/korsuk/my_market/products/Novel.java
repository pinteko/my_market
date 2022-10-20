package com.korsuk.my_market.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "novels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "novel_readers",
    joinColumns = @JoinColumn(name = "novel_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "price")
    private Double price;
}
