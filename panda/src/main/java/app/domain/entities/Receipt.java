package app.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@Getter
@Setter
public class Receipt extends BaseEntity {

    @Column(nullable = false, precision = 19 , scale = 4)
    private BigDecimal fee;

    @Column
    private LocalDateTime issuedOn;

    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User recipient;
}
