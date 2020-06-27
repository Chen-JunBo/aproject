package com.example.aproject.controller;



import com.example.aproject.component.EncryptComponent;
import com.example.aproject.component.MyToken;
import com.example.aproject.entity.User;
import com.example.aproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.student}")//@Value("${my.student}")
    private String roleStudent;
    @Value("${my.teacher}")
    private String roleTutor;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;



    @PostMapping("login")
    public Map login(@RequestBody User loginUser, HttpServletResponse response) {
        User user = Optional.ofNullable(userService.getUser(loginUser.getNumber()))
                .filter(u -> encoder.matches(loginUser.getPassword(),u.getPassword()))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误"));

        MyToken token  = new MyToken(user.getId(), user.getRole());
        String auth = encrypt.encryptToken(token);
        response.setHeader(MyToken.AUTHORIZATION, auth);

        String roleCode = user.getRole() == User.Role.TEACHER ? roleTutor : roleStudent;
        return Map.of("role",roleCode);
    }



//
}
