package app.mBeans;

import app.domain.models.binding.PackageCreateBindingModel;
import app.domain.models.view.UserViewModel;
import app.services.PackageService;
import app.services.UserService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
@NoArgsConstructor
public class PackageCreateManagedBean {
    private PackageCreateBindingModel packageCreateBindingModel;
    private PackageService packageService;
    private List<String> usernames;

    @Inject
    public PackageCreateManagedBean(PackageService packageService, UserService userService) {
        this.packageCreateBindingModel = new PackageCreateBindingModel();
        this.packageService = packageService;
        init(userService);
    }

    private void init(UserService userService) {
        this.usernames = userService.getAllUsers()
                .stream()
                .map(UserViewModel::getUsername)
                .collect(Collectors.toList());
    }

    public void create() throws IOException {
        packageService.createPackage(packageCreateBindingModel);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/user-home.xhtml");
    }

    public PackageCreateBindingModel getPackageCreateBindingModel() {
        return packageCreateBindingModel;
    }

    public void setPackageCreateBindingModel(PackageCreateBindingModel packageCreateBindingModel) {
        this.packageCreateBindingModel = packageCreateBindingModel;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
