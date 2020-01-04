package com.mkolongo.judgesystem.services.base;

import com.mkolongo.judgesystem.domain.dtos.StrategyImportDto;

public interface StrategyService {

    void importStrategies(StrategyImportDto[] strategyImportDtos);
}
