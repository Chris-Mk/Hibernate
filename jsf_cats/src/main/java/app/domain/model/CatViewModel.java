package app.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CatViewModel {

    private String name;
    private String breed;
    private String color;
    private String gender;
    private BigDecimal price;
    private String addedOn;

}
