package com.carrillo.auth.impl;

import com.carrillo.auth.data.Response;
import com.carrillo.auth.data.User;
import com.carrillo.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class AuthImpl {

    @Autowired
    private UserRepository repo;

    public AuthImpl() {
    }

    public Response login(User user) {
        String email = new String(Base64.getDecoder().decode(user.getEmail()));
        user.setEmail(email);
        String password = new String(Base64.getDecoder().decode(user.getPassword()));
        user.setPassword((password));
        try {
            List<User> userList = repo.findAll();
            List<User> foundUsers = userList.stream().filter(user1 -> user1.getEmail().equals(user.getEmail())).toList();
            if (foundUsers.isEmpty()){
                return new Response(400, "Password or Email do not match");
            }
            User foundUser = foundUsers.getFirst();
            if (foundUser.getEmail() == null || foundUser.getPassword() == null){
                return new Response(400, "You must enter a email and password");
            }
            if (!foundUser.getEmail().equals(user.getEmail()) || !foundUser.getPassword().equals(user.getPassword())){
                return new Response(400, "Password or Email do not match");
            }
        } catch (Exception e){
            return new Response(400, e.getMessage());
        }
        return new Response(200, null);
    }

    public Response register(User user) {
        String email = new String(Base64.getDecoder().decode(user.getEmail()));
        user.setEmail(email);
        String password = new String(Base64.getDecoder().decode(user.getPassword()));
        user.setPassword((password));
        try {
            repo.save(user);
        } catch (Exception e){
            return new Response(400, e.getMessage());
        }
        return new Response(200, null);
    }

}
