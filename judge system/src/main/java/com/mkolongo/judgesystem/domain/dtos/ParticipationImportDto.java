package com.mkolongo.judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipationImportDto {

    @XmlElement(name = "contest")
    private ContestIdDto contestId;

    @XmlElement(name = "user")
    private UserIdDto userId;

    public ContestIdDto getContestId() {
        return contestId;
    }

    public void setContestId(ContestIdDto contestId) {
        this.contestId = contestId;
    }

    public UserIdDto getUserId() {
        return userId;
    }

    public void setUserId(UserIdDto userId) {
        this.userId = userId;
    }
}
