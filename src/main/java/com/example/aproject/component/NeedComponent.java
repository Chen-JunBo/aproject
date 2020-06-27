package com.example.aproject.component;

import com.example.aproject.entity.Teacher;
import com.example.aproject.entity.User;
import com.example.aproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


@Component
@Slf4j
public class NeedComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        int number = 1001;
        User user = userService.getUser(number);
        if(user ==null){
            User u = new User();
            u.setName("BO");
            u.setNumber(number);
            u.setRole(User.Role.TEACHER);
            u.setPassword(encoder.encode(String.valueOf(number)));
            Teacher t = new Teacher();
            t.setUser(u);
            userService.addTeacher(t);
        }
    }
    public int getUid(){
        return (int) RequestContextHolder.currentRequestAttributes()
                //springmvc提供的可以直接获取当前线程的方法，直接获取当前线程所有的attribute。
                .getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST );
        //在请求范围内获取MyToken.UID
    }
    public User.Role getRole(){
        return (User.Role) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }
}
