package com.korsuk.my_market.products;

import lombok.AllArgsConstructor;
import lombok.Data; //for Getter and Setter
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @ManyToMany
    @JoinTable(name = "novel_readers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id"))
    private List<Novel> novels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Novel> getNovels() {
        return novels;
    }

    public void setNovels(List<Novel> novels) {
        this.novels = novels;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //    private Cart cart;

}
