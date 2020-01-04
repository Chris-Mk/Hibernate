package app.mBeans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.services.UserService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Named
@RequestScoped
@NoArgsConstructor
public class UserRegisterManagedBean {
    private UserRegisterBindingModel userRegisterBindingModel;
    private UserService userService;

    @Inject
    public UserRegisterManagedBean(UserService userService) {
        this.userService = userService;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public void register() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        userService.registerUser(userRegisterBindingModel);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/login.xhtml");
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }
}
