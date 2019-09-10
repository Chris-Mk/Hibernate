package entities.shampoos;

import entities.BasicLabel;
import entities.ingredients.*;
import helpers.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "pink_panther")
public class PinkPanther extends BasicShampoo {
    private static final String BRAND = "Pink Panther";
    private static final BigDecimal PRICE = new BigDecimal(8.50);
    private static final Size SIZE = Size.MEDIUM;
    private static final Set<BasicIngredient> INGREDIENTS = Set.of(new Lavender(), new Nettle());

    public PinkPanther() {
    }

    public PinkPanther(BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
        this.setIngredients(INGREDIENTS);
    }
}
