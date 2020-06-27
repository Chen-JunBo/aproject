package com.example.aproject.interceptor;




import com.example.aproject.component.NeedComponent;
import com.example.aproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TeacherInterceptor implements HandlerInterceptor {
    @Autowired
    private NeedComponent requestComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(requestComponent.getRole() != User.Role.TEACHER){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"无权限");
        }
        return true;
    }
}
