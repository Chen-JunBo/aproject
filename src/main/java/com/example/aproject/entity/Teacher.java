package com.example.aproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    private int  id;
    private String name = "wangbo";
    private String password = "root";
    private int num;//导师所带人数
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;


}
