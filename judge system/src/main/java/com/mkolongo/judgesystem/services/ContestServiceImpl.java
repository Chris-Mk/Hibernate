package com.mkolongo.judgesystem.services;

import com.mkolongo.judgesystem.domain.dtos.ContestImportDto;
import com.mkolongo.judgesystem.domain.dtos.StrategyImportDto;
import com.mkolongo.judgesystem.domain.models.Category;
import com.mkolongo.judgesystem.domain.models.Contest;
import com.mkolongo.judgesystem.domain.models.Strategy;
import com.mkolongo.judgesystem.repositories.CategoryRepository;
import com.mkolongo.judgesystem.repositories.ContestRepository;
import com.mkolongo.judgesystem.repositories.StrategyRepository;
import com.mkolongo.judgesystem.services.base.ContestService;
import com.mkolongo.judgesystem.util.base.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ContestServiceImpl implements ContestService {

    private final CategoryRepository categoryRepository;
    private final StrategyRepository strategyRepository;
    private final ContestRepository contestRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ContestServiceImpl(CategoryRepository categoryRepository, StrategyRepository strategyRepository,
                              ContestRepository contestRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.strategyRepository = strategyRepository;
        this.contestRepository = contestRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importContests(ContestImportDto[] contestImportDtos) {

        for (ContestImportDto contestImportDto : contestImportDtos) {
            if (!validationUtil.isValid(contestImportDto)) {
                validationUtil.getViolations(contestImportDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            final Contest contest = modelMapper.map(contestImportDto, Contest.class);

            final Category category = categoryRepository.findById(contestImportDto.getCategory().getId()).orElse(null);
            if (category == null) {
                System.out.println("Category doesn't exist!");
                continue;
            }

            contest.setCategory(category);

            Set<Strategy> strategies = new LinkedHashSet<>();
            for (StrategyImportDto strategy : contestImportDto.getStrategies()) {
                final Strategy strategyByName = strategyRepository.findStrategyByName(strategy.getName());

                if (strategyByName == null) {
                    System.out.println("Strategy doesn't exist!");
                    continue;
                }
                strategies.add(strategyByName);
            }

            contest.setStrategies(strategies);
            contestRepository.saveAndFlush(contest);
        }
    }
}
