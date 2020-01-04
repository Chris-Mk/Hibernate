package com.mkolongo.judgesystem.services;

import com.mkolongo.judgesystem.domain.dtos.ParticipationImportDto;
import com.mkolongo.judgesystem.domain.dtos.ParticipationListDto;
import com.mkolongo.judgesystem.domain.dtos.UserImportDto;
import com.mkolongo.judgesystem.domain.models.Contest;
import com.mkolongo.judgesystem.domain.models.User;
import com.mkolongo.judgesystem.repositories.ContestRepository;
import com.mkolongo.judgesystem.repositories.UserRepository;
import com.mkolongo.judgesystem.services.base.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final ContestRepository contestRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(ContestRepository contestRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.contestRepository = contestRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importUsers(UserImportDto[] userImportDtos) {
        final User[] users = modelMapper.map(userImportDtos, User[].class);
        userRepository.saveAll(Arrays.asList(users));
    }

    @Override
    public void enrollUsers(ParticipationListDto participationListDto) {
        Map<User, Set<Long>> userIntegerMap = new LinkedHashMap<>();

        for (ParticipationImportDto participationImportDto : participationListDto.getPartication()) {
            final User user = userRepository.findById(participationImportDto.getUserId().getId()).orElse(null);

            userIntegerMap.putIfAbsent(user, new LinkedHashSet<>());
            if (userIntegerMap.get(user).contains(participationImportDto.getContestId().getId())) {
                System.out.println("User already has enrolled to contest");
                continue;
            }

            userIntegerMap.get(user).add(participationImportDto.getContestId().getId());
        }

        for (var userSetEntry : userIntegerMap.entrySet()) {
            Set<Contest> contests = new LinkedHashSet<>();
            userSetEntry.getValue()
                    .forEach(contestId -> {
                        contestRepository.findById(contestId).ifPresent(contests::add);
                    });

            final User user = userSetEntry.getKey();
            user.setContests(contests);

            userRepository.saveAndFlush(user);
        }
    }
}
