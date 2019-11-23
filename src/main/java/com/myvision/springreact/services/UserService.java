package com.myvision.springreact.services;

import com.myvision.springreact.domain.User;
import com.myvision.springreact.exceptions.UserAlreadyExistException;
import com.myvision.springreact.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User saveUser(User newUser) {

        //username must be unique( exception)

        try {
// pass and cpass matched
            //
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UserAlreadyExistException("User Already Exist");
        }

    }
}
