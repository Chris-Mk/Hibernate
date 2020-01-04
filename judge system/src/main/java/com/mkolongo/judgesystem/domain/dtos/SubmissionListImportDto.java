package com.mkolongo.judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "submissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmissionListImportDto {

    @XmlElement(name = "submission")
    private List<SubmissionImportDto> submissions;

    public List<SubmissionImportDto> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionImportDto> submissions) {
        this.submissions = submissions;
    }
}
