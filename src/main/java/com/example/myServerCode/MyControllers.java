package com.example.myServerCode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Rest Api
public class MyControllers {
    HashMap<Integer, User> users = new HashMap<>();

    //http://localhost:8080/get_all_users
    @GetMapping("get_all_users")
    public List<User> getAllUsers(){
        List<User> listOfUsers = new ArrayList<>();

        for(Map.Entry<Integer, User> entry : users.entrySet()){
            listOfUsers.add(entry.getValue());
        }

        return listOfUsers;
    }

    //http://localhost:8080/get_user?id=1
    @GetMapping("/get_user")
    public User getUser(@RequestParam("id") int id){
        return users.get(id);
    }

    //http://localhost:8080/add_user?id=1&name=Chetan&country=India&age=24
    @PostMapping("/add_user")
    public void addUser(@RequestParam("id") int id,@RequestParam("name") String name, @RequestParam("country") String country, @RequestParam("age") int age ){

        User user = new User(id,name,country,age);
        users.put(id, user);
    }
    @PostMapping("/add_user_body")
    public void addUserBody(@RequestBody(required = true) User user){
        users.put(user.getId(), user);
    }

    @DeleteMapping("delete_user/{id}")
    public void deleteUser(@PathVariable("id") int id){
        users.remove(id);
    }

}

