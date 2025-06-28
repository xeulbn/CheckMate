package org.example.checkmate.user.service;

import lombok.AllArgsConstructor;

import org.example.checkmate.user.domain.Role;
import org.example.checkmate.user.domain.User;
import org.example.checkmate.user.repository.RoleRepository;
import org.example.checkmate.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public User registerUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(user.getName());
        user.setMajor(user.getMajor());
        Role userRole;
        if(isEmail(user.getUsername())){
            userRole=roleRepository.findByName("PROFESSOR");
        }else{
            userRole=roleRepository.findByName("STUDENT");
        }
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Transactional
    public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    private boolean isEmail(String username) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return username.matches(emailRegex);
    }
}