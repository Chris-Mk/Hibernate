package com.mkolongo.judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "participations")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipationListDto {

    @XmlElement(name = "participation")
    private List<ParticipationImportDto> partication;

    public List<ParticipationImportDto> getPartication() {
        return partication;
    }

    public void setPartication(List<ParticipationImportDto> partication) {
        this.partication = partication;
    }
}
