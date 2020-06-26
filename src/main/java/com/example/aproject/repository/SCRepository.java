package com.example.aproject.repository;

import com.example.aproject.entity.SC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SCRepository extends BaseRepository<SC, Integer> {
    @Query("select sc.score from SC sc where sc.courseName=:name AND sc.student.id=:sid")
    Double findScoreByCourseName(@Param("name") String name, @Param("sid") int sid);

}
