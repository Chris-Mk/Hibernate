package entities.shampoos;

import entities.BasicLabel;
import entities.ingredients.BasicIngredient;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.ingredients.chemicalIngredients.AmmoniumChloride;
import helpers.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "fresh_nuke")
public class FreshNuke extends BasicShampoo {
    private static final String BRAND = "Fresh Nuke";
    private static final BigDecimal PRICE = new BigDecimal(9.33);
    private static final Size SIZE = Size.LARGE;
    private static final Set<BasicIngredient> INGREDIENTS = Set.of(new Mint(), new Nettle(), new AmmoniumChloride());

    public FreshNuke() {
    }

    public FreshNuke(BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
        this.setIngredients(INGREDIENTS);
    }
}
