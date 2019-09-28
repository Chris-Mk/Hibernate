package app.ccb.domain.dtos;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountImportDto {

    @XmlAttribute(name = "client")
    private String client;

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement
    private BigDecimal balance;

    public BankAccountImportDto() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
