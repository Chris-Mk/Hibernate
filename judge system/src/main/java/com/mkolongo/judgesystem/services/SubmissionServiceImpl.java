package com.mkolongo.judgesystem.services;

import com.mkolongo.judgesystem.domain.dtos.SubmissionImportDto;
import com.mkolongo.judgesystem.domain.dtos.SubmissionListImportDto;
import com.mkolongo.judgesystem.domain.models.*;
import com.mkolongo.judgesystem.repositories.*;
import com.mkolongo.judgesystem.services.base.SubmissionService;
import com.mkolongo.judgesystem.util.base.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final MaxResultPerProblemRepository maxResultPerProblemRepository;
    private final SubmissionRepository submissionRepository;
    private final StrategyRepository strategyRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SubmissionServiceImpl(MaxResultPerProblemRepository maxResultPerProblemRepository,
                                 SubmissionRepository submissionRepository, StrategyRepository strategyRepository,
                                 ProblemRepository problemRepository, UserRepository userRepository,
                                 ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.maxResultPerProblemRepository = maxResultPerProblemRepository;
        this.submissionRepository = submissionRepository;
        this.strategyRepository = strategyRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void importSubmissions(SubmissionListImportDto submissionListImportDto) {
        Map<User, Set<Problem>> userSetMap = new LinkedHashMap<>();
        List<MaxResultPerProblem> maxResultPerProblems = new ArrayList<>();

        for (SubmissionImportDto submission : submissionListImportDto.getSubmissions()) {
            if (!validationUtil.isValid(submission)) {
                validationUtil.getViolations(submission)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            final Problem problem = problemRepository.getOne(submission.getProblemId().getId());
            final Strategy strategy = strategyRepository.getOne(submission.getStrategyId().getId());

            if (!problem.getContest().getStrategies().contains(strategy)) {
                System.out.println("Strategy doesn't exist!");
                continue;
            }

            final User user = userRepository.getOne(submission.getUserId().getId());
            if (!user.getContests().contains(problem.getContest())) {
                System.out.println("User is not contestant in contest!");
                continue;
            }

            userSetMap.putIfAbsent(user, new LinkedHashSet<>());
            userSetMap.get(user).add(problem);

            final Submission submission1 = modelMapper.map(submission, Submission.class);
            submission1.setProblem(problem);
            submission1.setStrategy(strategy);
            submission1.setUser(user);
            submissionRepository.saveAndFlush(submission1);

            MaxResultPerProblem maxResultPerProblem = new MaxResultPerProblem();
            maxResultPerProblem.setUser(user);
            maxResultPerProblem.setProblem(problem);
            maxResultPerProblem.setSubmission(submission1);

//            maxResultPerProblemRepository.findMaxResultPerProblemBySubmission(submission1);
//            maxResultPerProblemRepository.saveAndFlush(maxResultPerProblem);
//            if (maxResultPerProblems.contains(maxResultPerProblem)) {
//                maxResultPerProblems
//            }
            maxResultPerProblems.add(maxResultPerProblem);
        }

        userSetMap.forEach((user, value) -> {
            user.setProblems(value);
            userRepository.saveAndFlush(user);
        });
    }
}
