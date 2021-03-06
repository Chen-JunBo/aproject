package com.example.aproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.aproject.component.NeedComponent;
import com.example.aproject.entity.*;
import com.example.aproject.service.StudentService;
import com.example.aproject.service.TeacherService;
import com.example.aproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {
    @Autowired
    TeacherService ts;
    @Autowired
    NeedComponent rc;
    @Autowired
    StudentService ss;
    @Autowired
    UserService us;
    @GetMapping("index")
    public Map index() {
        return Map.of("teacher",ts.getTeacher(rc.getUid()));
    }
    @PostMapping("adddirection")
    public Map postDirection(@RequestBody Direction direction) {

        if(direction.getName() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"方向不能为空");
        Teacher t = ts.getTeacher(rc.getUid());
        direction.setTeacher(t);
        direction = ts.postDirection(direction);
       return Map.of("direction",direction);
    }
    @GetMapping("directions/{did}")
    public Map getDirection(@PathVariable int did) {
        return Map.of("direction",ts.getDirection(did));
    }
   @PostMapping("addcourse")
   public Map postCourse( @RequestBody Course course) {
       Direction direction = ts.getDirection(course.getDirection().getId());
       if (direction != null) {
           for(Course c : direction.getCourses() )
               if(c.getName().equals(course.getName()))
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"已添加过该课程");
           course.setDirection(direction);
           course.setTeacher(ts.getTeacher(rc.getUid()));
           ts.postCourse(course);
           return Map.of("course",course);
       }
       else
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"方向不存在");
   }
    @GetMapping("listdirections")
    public Map listDirections() {
        return Map.of("listDirections", ts.getDirections(rc.getUid()));
    }
    @GetMapping("directions/{did}/listcourses")
    public Map listCourses(@PathVariable int did) {
      return Map.of("listCourses", ts.getDirection(did).getCourses());
    }
    @PostMapping("addstudent")
    public Map postStudent(@RequestBody Student student) {
       return Map.of("student",ts.addStudent(student.getId()));
    }
    @GetMapping("directions/{did}/liststudents")
    public Map listStudent(@PathVariable int did) {
        List<Map.Entry<Integer, Double>> listStudent = ts.listStudent(did);
        List<Student> students = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        for(Map.Entry s : listStudent) {
            Student student = ts.getStudentByNumber((Integer) s.getKey());
            Double value = (Double) s.getValue();
            students.add(student);
            scores.add(value);
        }

        return Map.of("students", students,
                      "studentsScore",scores);
    }
    @PostMapping("delStudent")
    public Map deleteStudent(@RequestBody Student student) {
        return Map.of("student", ts.deleteStudent(student.getId()));
    }
    @GetMapping("liststudent")
    public Map listStudent() {
        return Map.of("listStudents",ts.getTeacher(rc.getUid()).getStudents());
    }

    @PostMapping("setstucap")
    public int setSetCap(@RequestBody TeacherV teacherVO) {

        return ts.setStuCap(teacherVO.getStuCap());
    }
    @GetMapping("getstucap")
    public Map getStuCap() {
        return Map.of("stuCap", ts.getTeacher(rc.getUid()).getStuCap());
    }
    @GetMapping("getstunum")
    public Map getStuNum() {
        return Map.of("stuNum", ts.getTeacher(rc.getUid()).getStuCap() - ts.getTeacher(rc.getUid()).getStuNum());
    }
    @PostMapping("importUser")
    public void addUser(@RequestBody String users) {
       List<User> userList = (List<User>) JSONArray.parseArray(users,User.class);
        us.importUser(userList);
    }
    @PostMapping("importSC")
    public void addSC(@RequestBody String scs) {
        List<SC> scList = (List<SC>) JSON.parseArray(scs,SC.class);
        us.importSC(scList);
    }


}
