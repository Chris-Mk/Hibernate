package app.services;

import app.domain.entities.Status;
import app.domain.models.binding.PackageCreateBindingModel;
import app.domain.models.view.PackageViewModel;

import java.util.List;

public interface PackageService {

    void createPackage(PackageCreateBindingModel packageCreateBindingModel);

    List<PackageViewModel> getAllPackagesWithStatus(Status status);

    void updatePackageStatus(Long id, Status status);
}
