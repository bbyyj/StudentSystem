package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monograph")
public class Monograph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title; // 专著名称

    @Column(name = "author_type")
    private String author_type; // 第几作者

    @Column(name = "authors")
    private String authors; // 该著作全部作者

    @Column(name = "publisher")
    private String publisher; // 出版单位

    @Column(name = "category")
    private String category; // 著作类别

    @Column(name = "isbn")
    private String isbn; // ISBN

    @Column(name = "pub_date")
    private Date pub_date; // 出版时间

    @Column(name = "remark")
    private String remark; // 备注
}
