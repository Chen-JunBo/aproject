package com.example.aproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"courses", "students","directions"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    private User user;//依靠单向@OneToOne和@MapsId，与父表共享主键
    //人数上限
    private Integer stuCap;
    //当前人数
    private int stuNum;
    //学生范围数(根据排名确定)
    private int scope;

    //private int scopeStuNum;
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
    @OneToMany(mappedBy = "teacher")
    private List<Course>  courses;
    @OneToMany(mappedBy = "teacher")
    private List<Direction> directions;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;
}
