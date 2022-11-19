package com.simplenoteapp.noteapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1/users")
public class UserController {
    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping()
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @PutMapping( path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) Integer age){
        userService.updateUser(userId, name, email, age);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
}
