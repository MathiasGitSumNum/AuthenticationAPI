package com.carrillo.auth.rest;

import com.carrillo.auth.data.Response;
import com.carrillo.auth.data.User;
import com.carrillo.auth.impl.AuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthImpl authImpl;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Response login(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = new User(email, password);
        return authImpl.login(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public Response register(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = new User(email, password);
        return authImpl.register(user);
    }

}
