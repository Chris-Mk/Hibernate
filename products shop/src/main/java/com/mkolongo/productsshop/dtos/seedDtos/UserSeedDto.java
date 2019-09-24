package com.mkolongo.productsshop.dtos.seedDtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class UserSeedDto {

    @Expose
    private String firstName;

    @Expose
    @Length(min = 3, message = "Last name must be at least 3 characters long!")
    private String lastName;

    @Expose
    private int age;

    @Override
    public String toString() {
        return "UserSeedDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
