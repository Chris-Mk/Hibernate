package entities;

import abstractions.Label;
import entities.shampoos.BasicShampoo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {

    @Id
    @GeneratedValue(generator = "incrementer")
    @GenericGenerator(name = "incrementer", strategy = "increment")
    private long id;

    @Column
    private String title;

    @Column
    private String subtitle;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
    private BasicShampoo shampoo;

    public BasicLabel() {
    }

    public BasicLabel(String title, String subtitle) {
        this.setTitle(title);
        this.setSubtitle(subtitle);
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubtitle() {
        return this.subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public BasicShampoo getShampoo() {
        return shampoo;
    }

    @Override
    public void setShampoo(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }
}
