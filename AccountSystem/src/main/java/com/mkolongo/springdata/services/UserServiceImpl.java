package com.mkolongo.springdata.services;

import com.mkolongo.springdata.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mkolongo.springdata.repositories.UserRepository;
import com.mkolongo.springdata.services.base.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("User already exist!");
        }

        userRepository.save(user);
    }
}
