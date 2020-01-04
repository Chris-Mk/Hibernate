package app.mBeans;

import app.domain.entities.Status;
import app.domain.models.view.PackageViewModel;
import app.services.PackageService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
@Getter
@Setter
public class PackageViewManagedBean {
    private List<PackageViewModel> packageViewModels;
    private PackageService packageService;

    @Inject
    public PackageViewManagedBean(PackageService packageService) {
        this.packageService = packageService;
        init();
    }

    private void init() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();

        String[] paths = httpServletRequest.getRequestURI().split("/");
        String lastPath = paths[paths.length - 1];
        String status = lastPath.substring(0, lastPath.indexOf("."));
        status = status.toUpperCase().charAt(0) + status.substring(1).toLowerCase();

        packageViewModels = packageService.getAllPackagesWithStatus(Status.valueOf(status));
    }

    public void ship(Long id) {
        packageService.updatePackageStatus(id, Status.Shipped);
    }

    public void deliver(Long id) {
        packageService.updatePackageStatus(id, Status.Delivered);
    }

    public void details(Long id) {

    }
}
