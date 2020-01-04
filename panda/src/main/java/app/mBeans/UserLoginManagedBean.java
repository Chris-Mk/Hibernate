package app.mBeans;

import app.domain.entities.Status;
import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.view.PackageViewModel;
import app.domain.models.view.UserViewModel;
import app.services.PackageService;
import app.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
@Getter
@Setter
public class UserLoginManagedBean {
    private UserLoginBindingModel userLoginBindingModel;
    private UserService userService;
    private List<PackageViewModel> pendingPackages;
    private List<PackageViewModel> shippedPackages;
    private List<PackageViewModel> deliveredPackages;

    @Inject
    public UserLoginManagedBean(UserService userService, PackageService packageService) {
        this.userService = userService;
        this.userLoginBindingModel = new UserLoginBindingModel();
        init(packageService);
    }

    private void init(PackageService packageService) {
        pendingPackages = packageService.getAllPackagesWithStatus(Status.Pending);
        shippedPackages = packageService.getAllPackagesWithStatus(Status.Shipped);
        deliveredPackages = packageService.getAllPackagesWithStatus(Status.Delivered);
    }

    public void login() throws IOException {
        UserViewModel userViewModel = userService.getUserByUsername(userLoginBindingModel.getUsername());

        if (userViewModel != null) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            HttpSession session = (HttpSession) externalContext.getSession(false);
            session.setAttribute("username", userViewModel.getUsername());
            session.setAttribute("role", userViewModel.getRole());

            externalContext.redirect("/faces/user-home.xhtml");
        }
    }
}
