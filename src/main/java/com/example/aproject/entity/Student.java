package com.example.aproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;//学号
    private String sname;
    @OneToMany(mappedBy = "student")
    private List<SC> scs;
    @ManyToOne
    private Teacher teacher;

}
