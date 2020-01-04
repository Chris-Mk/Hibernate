package com.mkolongo.judgesystem.services.base;

import com.mkolongo.judgesystem.domain.dtos.ContestImportDto;

public interface ContestService {

    void importContests(ContestImportDto[] contestImportDtos);
}
