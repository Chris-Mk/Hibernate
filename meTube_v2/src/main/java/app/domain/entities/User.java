package app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private char[] password;
    private String email;
    private Collection<Tube> tubes;

    @Column(unique = true, nullable = false, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "uploader")
    public Collection<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(Collection<Tube> tubes) {
        this.tubes = tubes;
    }
}
