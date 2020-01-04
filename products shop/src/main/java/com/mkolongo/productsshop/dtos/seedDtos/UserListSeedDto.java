package com.mkolongo.productsshop.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserListSeedDto {

    @XmlElement(name = "user")
    private List<UserSeedDto> users;
}
