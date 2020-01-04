package app.domain.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PackageViewModel {
    private Long id;
    private String description;
    private double weight;
    private String shippingAddress;
    private String recipient;
    private LocalDateTime estimatedDeliveryDate;
}
