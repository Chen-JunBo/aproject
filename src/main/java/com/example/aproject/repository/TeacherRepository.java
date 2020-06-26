package com.example.aproject.repository;

import com.example.aproject.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends BaseRepository<Teacher, Integer> {

    @Query("from Teacher t where t.id=:tid")
    Teacher find(@Param("tid") int tid);
}
