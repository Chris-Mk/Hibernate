package entities.shampoos;

import entities.BasicLabel;
import entities.ingredients.BasicIngredient;
import entities.ingredients.Nettle;
import entities.ingredients.Strawberry;
import helpers.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "fifty_shades")
public class FiftyShades extends BasicShampoo {
    private static final String BRAND = "Fifty Shades";
    private static final BigDecimal PRICE = new BigDecimal(6.69);
    private static final Size SIZE = Size.SMALL;
    private static final Set<BasicIngredient> INGREDIENTS = Set.of(new Strawberry(), new Nettle());

    public FiftyShades() {
    }

    public FiftyShades(BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
        this.setIngredients(INGREDIENTS);
    }
}
