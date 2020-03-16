package com.example.aproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;
    @OneToMany(mappedBy = "course")
    private List<SC> scs;
/*
    @OneToMany(mappedBy = "course")
    private List<DC> dc;
    * */
}
