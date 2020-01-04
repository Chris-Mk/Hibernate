package app.domain.models.view;

import app.domain.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewModel {
    private String username;
    private String email;
    private Role role;
}
