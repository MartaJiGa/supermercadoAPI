package com.svalero.supermercadoAPI.service;

import com.svalero.supermercadoAPI.domain.User;
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
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public List<User> getUserByName(String name){
        return userRepository.findByName(name);
    }
    public List<User> getUserByPrice(String surname){
        return userRepository.findBySurname(surname);
    }
    public List<User> getUserByNameAndPrice(String name, String surname){
        return userRepository.findByNameAndSurname(name,surname);
    }
    //endregion

    //region POST requests
    public void saveUser(User user){
        userRepository.save(user);
    }
    //endregion

    //region PUT requests
    //endregion

    //region DELETE requests
    //endregion
}
