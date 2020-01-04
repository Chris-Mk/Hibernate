package app.domain.models.view;

import app.domain.entities.Tube;

import java.util.Collection;

public class UserViewModel {
    private String username;
    private String email;
    private Collection<Tube> tubes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(Collection<Tube> tubes) {
        this.tubes = tubes;
    }
}
