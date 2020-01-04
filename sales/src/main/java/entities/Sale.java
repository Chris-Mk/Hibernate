package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    private int id;
    private int productId;
    private int customerId;
    private int storeLocationId;
    private LocalDateTime date;

    public Sale() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JoinColumn(name = "product_id",  referencedColumnName = "id")
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = StoreLocation.class, fetch = FetchType.LAZY)
    public int getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(int storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    @Column
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
