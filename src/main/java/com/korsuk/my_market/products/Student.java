package com.korsuk.my_market.products;

import lombok.AllArgsConstructor;
import lombok.Data; //for Getter and Setter
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.*;
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;


//    private Cart cart;

}
