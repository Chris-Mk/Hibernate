package app.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "packages")
@Getter
@Setter
public class Package extends BaseEntity {

    @Column
    private String description;

    @Column(precision = 19, scale = 2)
    private double weight;

    @Column(nullable = false)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private LocalDateTime estimatedDeliveryDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User recipient;
}
