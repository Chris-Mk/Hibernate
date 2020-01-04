package entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "billing_type")
public abstract class BillingDetail extends BaseEntity {

    private int number;
    private Set<User> owners;

    public BillingDetail() {
    }

    @Column
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToMany(mappedBy = "billingDetails", targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<User> getOwners() {
        return Collections.unmodifiableSet(owners);
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }
}
