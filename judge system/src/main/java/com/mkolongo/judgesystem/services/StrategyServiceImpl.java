package com.mkolongo.judgesystem.services;

import com.mkolongo.judgesystem.domain.dtos.StrategyImportDto;
import com.mkolongo.judgesystem.domain.models.Strategy;
import com.mkolongo.judgesystem.repositories.StrategyRepository;
import com.mkolongo.judgesystem.services.base.StrategyService;
import com.mkolongo.judgesystem.util.base.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public StrategyServiceImpl(StrategyRepository strategyRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.strategyRepository = strategyRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importStrategies(StrategyImportDto[] strategyImportDtos) {

        for (StrategyImportDto strategyImportDto : strategyImportDtos) {
            if (!validationUtil.isValid(strategyImportDto)) {
                validationUtil.getViolations(strategyImportDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            final Strategy strategy = modelMapper.map(strategyImportDto, Strategy.class);
            strategyRepository.saveAndFlush(strategy);
        }
    }
}
