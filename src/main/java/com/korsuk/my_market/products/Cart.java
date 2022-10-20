package com.korsuk.my_market.products;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
//   private List<Novel> novels;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @Column (name = "purchase_date")
    private Date date;



    @PostConstruct
    public void init() {
//        novels = new ArrayList<>();
//        dateToAdd = new Date();
    }

//    public void addBook(Novel novel) {
//        if (novel != null) {
//            novels.add(novel);}
//    }
//
//    public void deleteBook(Novel novel) {
//        if (novel != null) {
//            novels.remove(novel);}
//    }
}
