package app.ccb.domain.dtos;

import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;

import java.util.List;

public class FamilyGuyExportDto {

    private String fullName;
    private int age;
    private BankAccount bankAccount;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Full Name: %s%n", getFullName()))
                .append(String.format("Age: %d%n", getAge()))
                .append(String.format("Bank Account: %s%n", getBankAccount().getAccountNumber()));

        for (Card card : getBankAccount().getCards()) {
            builder.append("\t")
                    .append(String.format("Card Number %s%n", card.getCardNumber()))
                    .append(String.format("Card Status %s%n", card.getCardStatus()));
        }

        return builder.toString().trim();
    }
}
