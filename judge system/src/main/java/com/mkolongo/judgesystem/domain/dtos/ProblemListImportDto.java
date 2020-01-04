package com.mkolongo.judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "problems")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemListImportDto {

    @XmlElement(name = "problem")
    private List<ProblemImportDto> problems;

    public List<ProblemImportDto> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemImportDto> problems) {
        this.problems = problems;
    }
}
