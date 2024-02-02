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
    public List<User> getUserBySurname(String surname){
        return userRepository.findBySurname(surname);
    }
    public List<User> getUserByNameAndSurname(String name, String surname){
        return userRepository.findByNameAndSurname(name,surname);
    }
    //endregion

    //region POST requests
    public void saveUser(User user){
        userRepository.save(user);
    }
    //endregion

    //region PUT requests
    public void modifyUser(User newUser, long userId){
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            User existingUser = user.get();

            existingUser.setName(newUser.getName());
            existingUser.setSurname(newUser.getSurname());
            existingUser.setDateOfBirth(newUser.getDateOfBirth());
            existingUser.setEmail(newUser.getEmail());
        }
    }
    //endregion

    //region DELETE requests
    public void removeUser(long userId){
        userRepository.deleteById(userId);
    }
    //endregion
}
