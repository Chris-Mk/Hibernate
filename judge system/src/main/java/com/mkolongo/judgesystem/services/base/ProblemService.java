package com.mkolongo.judgesystem.services.base;

import com.mkolongo.judgesystem.domain.dtos.ProblemListImportDto;

public interface ProblemService {

    void importProblems(ProblemListImportDto problemListImportDto);
}
