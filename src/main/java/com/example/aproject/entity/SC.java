package com.example.aproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class SC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    private double grade;
    private double weight;

}
