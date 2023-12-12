package com.ecommerce.apparel.service;

import com.ecommerce.apparel.dao.UserDao;
import com.ecommerce.apparel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDao userDao;
    public String register(User user) {
        if(userDao.findByEmail(user.getEmail()) != null) {
            return "Username already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.save(user);

        return "username: "+user.getEmail()+" registered successfully!";
    }

    public String login(Map<String, String> loginData) {
        String userName = loginData.get("userName");
        String password = loginData.get("password");

        User existingUser = userDao.findByEmail(userName);

        if( existingUser==null || !verifyPassword(password,existingUser.getPassword())){
            return "Invalid username or password!";
        }

        return "Successfully logged in!";
    }

    private boolean verifyPassword(String providedPassword, String actualPassword){
        return BCrypt.checkpw(providedPassword,actualPassword);
    }

    public List<String> getHomeData() {
        return userDao.returnAllUsername();
    }
}
