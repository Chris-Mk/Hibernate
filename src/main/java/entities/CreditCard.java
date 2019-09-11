package entities;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;

@Entity
@DiscriminatorValue(value = "CC")
public class CreditCard extends BillingDetail {

    private String cardType;
    private Month expirationMonth;
    private Year expirationYear;

    public CreditCard() {
    }

    @Column(name = "card_type")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "expiration_month")
    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name = "expiration_year")
    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }
}
