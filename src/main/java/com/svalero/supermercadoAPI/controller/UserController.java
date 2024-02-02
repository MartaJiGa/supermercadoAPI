package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.User;
import com.svalero.supermercadoAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //region GET requests
    @GetMapping("/user/{userId}")
    public Optional<User> getUser(@PathVariable long userId){
        return userService.getUserById(userId);
    }
    @GetMapping("/users")
    public List<User> findAll(@RequestParam(defaultValue = "")String userName, @RequestParam(defaultValue = "")String surname){
        if(!userName.isEmpty() && surname.isEmpty()){
            return userService.getUserByName(userName);
        }
        else if(userName.isEmpty() && !surname.isEmpty()){
            return userService.getUserBySurname(surname);
        }
        else if(!userName.isEmpty() && !surname.isEmpty()){
            return userService.getUserByNameAndSurname(userName, surname);
        }
        return userService.getUsers();
    }
    //endregion

    //region POST requests
    //endregion

    //region PUT requests
    //endregion

    //region DELETE requests
    //endregion
}
