package app.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class CatCreateModel {

    @Size(min = 2, max = 10, message = "Name must contain at least 2 characters, shouldn’t be longer than 10.")
    private String name;

    @Size(min = 5, max = 20, message = "Breed must contain at least 5 characters, shouldn’t be longer than 20.")
    private String breed;

    private String color;

    @Min(value = 1, message = "Age must me above 1.")
    @Max(value = 31, message = "Age must be below 31.")
    private int age;

    private String gender;

    @DecimalMin(value = "0.01", message = "Price must be at least 0.01.")
    private BigDecimal price;

    private String addedOn;

    private boolean hasPassport;

}
