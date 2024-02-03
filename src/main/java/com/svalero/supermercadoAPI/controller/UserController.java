package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.ErrorResponse;
import com.svalero.supermercadoAPI.domain.User;
import com.svalero.supermercadoAPI.exception.UserNotFoundException;
import com.svalero.supermercadoAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //region GET requests
    @GetMapping("/user/{userId}")
    public Optional<User> getUser(@PathVariable long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }
    @GetMapping("/users")
    public List<User> findAll(@RequestParam(defaultValue = "")String userName, @RequestParam(defaultValue = "")String surname) throws UserNotFoundException {
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
    @PostMapping("/users")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
    //endregion

    //region PUT requests
    @PutMapping("/user/{userId}")
    public void modifyUser(@RequestBody User user, @PathVariable long userId) throws UserNotFoundException {
        userService.modifyUser(user, userId);
    }
    //endregion

    //region DELETE requests
    @DeleteMapping("/user/{userId}")
    public void removeUser(@PathVariable long userId) throws UserNotFoundException {
        userService.removeUser(userId);
    }
    //endregion

    //region EXCEPTION HANDLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> badRequestException(MethodArgumentNotValidException badRequestEx){
        ErrorResponse errorResponse = new ErrorResponse(400, badRequestEx.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException userNotFoundEx){
        ErrorResponse errorResponse = new ErrorResponse(404, userNotFoundEx.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> internalServerError(HttpServerErrorException.InternalServerError intServError){
        ErrorResponse errorResponse = new ErrorResponse(500, intServError.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion
}
