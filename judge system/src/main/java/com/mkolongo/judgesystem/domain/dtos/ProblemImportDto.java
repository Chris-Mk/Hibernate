package com.mkolongo.judgesystem.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemImportDto {

    @XmlElement
    private long id;

    @XmlElement
    @Length(min = 3, message = "Problem name must at least 3 symbols long!")
    @Pattern(regexp = "^[A-Z].*", message = "Problem name must start with capital letter!")
    private String name;

    @XmlElement(name = "contest")
    @NotNull(message = "Contest cannot be empty!")
    private ContestIdDto contestId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContestIdDto getContestId() {
        return contestId;
    }

    public void setContestId(ContestIdDto contestId) {
        this.contestId = contestId;
    }
}
