package com.mkolongo.judgesystem.services.base;

import com.mkolongo.judgesystem.domain.dtos.ParticipationListDto;
import com.mkolongo.judgesystem.domain.dtos.UserImportDto;

public interface UserService {

    void importUsers(UserImportDto[] userImportDtos);

    void enrollUsers(ParticipationListDto participationListDto);
}
