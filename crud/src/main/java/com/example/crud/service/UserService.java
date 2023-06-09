package com.example.crud.service;


import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User createUser(User user){
        BCryptPasswordEncoder Crypted = new BCryptPasswordEncoder();
        String encryptedPwd =  Crypted.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users){
        return userRepository.saveAll(users);
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        User existUser;
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()){
           existUser = userOptional.get();
           existUser.setName(user.getName());
           existUser.setEmail(user.getEmail());
           existUser.setPassword(user.getPassword());
           userRepository.save(existUser);
        }else{
            return new User();
        }
        return existUser;
    }

    public String deleteUserById(int id){
        userRepository.deleteById(id);
        return "User Deleted";
    }



}
