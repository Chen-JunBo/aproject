package com.example.aproject.repository;


import com.example.aproject.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends BaseRepository<Student, Integer>{

    @Query("from Student s where s.id=:sid")
    Student find(@Param("sid") int sid);

    @Query("select s from Student s where s.user.number=:number")
    Student findByNumber(@Param("number") int number);

}
