package com.mkolongo.productsshop.services;

import com.google.gson.Gson;
import com.mkolongo.productsshop.dtos.seedDtos.UserSeedDto;
import com.mkolongo.productsshop.models.User;
import com.mkolongo.productsshop.repositories.UserRepository;
import com.mkolongo.productsshop.services.base.UserService;
import com.mkolongo.productsshop.util.base.FileUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           Validator validator, FileUtil fileUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() == 0) {
            final String userJson = fileUtil.readFile("files/users.json");

            final UserSeedDto[] userSeedDto = gson.fromJson(userJson, UserSeedDto[].class);

            final Set<ConstraintViolation<UserSeedDto[]>> violations = validator.validate(userSeedDto);

            if (!violations.isEmpty()) {
                violations.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
                return;
            }

            final User[] users = modelMapper.map(userSeedDto, new TypeToken<User[]>() {}.getType());

            userRepository.saveAll(Arrays.asList(users));
        }
    }
}
