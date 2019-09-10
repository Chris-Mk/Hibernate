package abstractions;

import entities.ingredients.BasicIngredient;
import entities.BasicLabel;
import helpers.Size;

import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo {

    String getBrand();

    void setBrand(String brand);

    BasicLabel getLabel();

    void setLabel(BasicLabel label);

    long getId();

    void setId(long id);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    Set<BasicIngredient> getIngredients();

    void setIngredients(Set<BasicIngredient> ingredients);
}
