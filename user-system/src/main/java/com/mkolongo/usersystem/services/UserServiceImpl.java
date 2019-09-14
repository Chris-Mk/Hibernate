package com.mkolongo.usersystem.services;

import com.mkolongo.usersystem.models.User;
import com.mkolongo.usersystem.repositories.UserRepository;
import com.mkolongo.usersystem.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsersByEmailProvider(String emailProvider) {
        return this.userRepository.findAllByEmailEndingWith(emailProvider);
    }

    @Override
    public int removeInactiveUsers(LocalDateTime lastLoginTime) {
        this.userRepository.findAll()
                .stream()
                .peek(user -> {
                    if (user.getLastTimeLoggedIn().compareTo(lastLoginTime) < 0) {
                        user.setDeleted(true);
                    }
                })
                .forEach(this.userRepository::saveAndFlush);

        final List<User> allByDeletedTrue = this.userRepository.findAllByDeletedTrue();

        this.userRepository.deleteAll(allByDeletedTrue);

        return allByDeletedTrue.size();
    }
}
