package com.mkolongo.judgesystem.services.base;

import com.mkolongo.judgesystem.domain.dtos.SubmissionListImportDto;

public interface SubmissionService {

    void importSubmissions(SubmissionListImportDto submissionListImportDto);
}
