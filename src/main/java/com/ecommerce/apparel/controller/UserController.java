package com.ecommerce.apparel.controller;

import com.ecommerce.apparel.entity.User;
import com.ecommerce.apparel.security.JwtUtil;
import com.ecommerce.apparel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/landing")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> newRegistration(@RequestBody User user){
        String response = userService.register(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData){
        String response = userService.login(loginData);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/home")
    public ResponseEntity<List<String>> getHomeData(){
        List<String> response = userService.getHomeData();
        return ResponseEntity.ok(response);
    }



}
