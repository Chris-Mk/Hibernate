package com.mkolongo.judgesystem.services;

import com.mkolongo.judgesystem.domain.dtos.ProblemImportDto;
import com.mkolongo.judgesystem.domain.dtos.ProblemListImportDto;
import com.mkolongo.judgesystem.domain.models.Contest;
import com.mkolongo.judgesystem.domain.models.Problem;
import com.mkolongo.judgesystem.repositories.ContestRepository;
import com.mkolongo.judgesystem.repositories.ProblemRepository;
import com.mkolongo.judgesystem.services.base.ProblemService;
import com.mkolongo.judgesystem.util.base.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProblemServiceImpl(ContestRepository contestRepository, ProblemRepository problemRepository,
                              ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.contestRepository = contestRepository;
        this.problemRepository = problemRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importProblems(ProblemListImportDto problemListImportDto) {

        for (ProblemImportDto problem : problemListImportDto.getProblems()) {
            if (!validationUtil.isValid(problem)) {
                validationUtil.getViolations(problem)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            final Contest contest = contestRepository.findById(problem.getContestId().getId()).orElse(null);
            if (contest == null) {
                System.out.println("Contest doesn't exist!");
                continue;
            }

            final Problem problem1 = modelMapper.map(problem, Problem.class);
            problem1.setContest(contest);

            problemRepository.saveAndFlush(problem1);
        }
    }
}
