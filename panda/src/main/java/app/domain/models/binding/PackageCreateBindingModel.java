package app.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageCreateBindingModel {
    private String description;
    private double weight;
    private String shippingAddress;
    private String recipient;
}
