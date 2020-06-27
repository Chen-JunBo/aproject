package com.example.aproject.service;




import com.example.aproject.entity.Direction;
import com.example.aproject.entity.Student;
import com.example.aproject.repository.DirectionRepository;
import com.example.aproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository sr;
    @Autowired
    TeacherService ts;
    @Autowired
    DirectionRepository dr;

    public Student getStudent(int sid) {
        return sr.find(sid);
    }
    public Direction getDirection(int did) {
        return dr.findById(did).orElse(null);
    }


}
