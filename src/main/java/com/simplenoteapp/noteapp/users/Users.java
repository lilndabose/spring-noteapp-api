package com.simplenoteapp.noteapp.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Users {
    private static List<String> users = new ArrayList<>();
    @GetMapping("/users")
    public List<String> getAllUsers(){
        users= List.of("Daniel","Sergeo","Peet","MIT");

        return this.users;
    }
}
