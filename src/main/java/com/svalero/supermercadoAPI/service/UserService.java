package com.svalero.supermercadoAPI.service;

import com.svalero.supermercadoAPI.domain.User;
import com.svalero.supermercadoAPI.exception.UserNotFoundException;
import com.svalero.supermercadoAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //region GET requests
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id);
    }
    public List<User> findByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name);
    }
    public List<User> findBySurname(String surname) throws UserNotFoundException {
        return userRepository.findBySurname(surname);
    }
    public List<User> findByNameAndSurname(String name, String surname){
        return userRepository.findByNameAndSurname(name,surname);
    }
    //endregion

    //region POST requests
    public void saveUser(User user){
        userRepository.save(user);
    }
    //endregion

    //region PUT requests
    public void modifyUser(User newUser, long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());

        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setDateOfBirth(newUser.getDateOfBirth());
        user.setEmail(newUser.getEmail());
    }
    //endregion

    //region DELETE requests
    public void removeUser(long userId){
        userRepository.deleteById(userId);
    }
    //endregion
}