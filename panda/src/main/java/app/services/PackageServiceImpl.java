package app.services;

import app.domain.entities.Package;
import app.domain.entities.Status;
import app.domain.entities.User;
import app.domain.models.binding.PackageCreateBindingModel;
import app.domain.models.view.PackageViewModel;
import app.repositories.PackageRepository;
import app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.util.List;

public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPackage(PackageCreateBindingModel packageCreateBindingModel) {
        Package aPackage = modelMapper.map(packageCreateBindingModel, Package.class);
        User user = userRepository.findByUsername(packageCreateBindingModel.getRecipient());
        aPackage.setRecipient(user);
        aPackage.setStatus(Status.Pending);
        packageRepository.save(aPackage);
    }

    @Override
    public List<PackageViewModel> getAllPackagesWithStatus(Status status) {
        return modelMapper.map(packageRepository.findAllWithStatus(status), new TypeToken<List<PackageViewModel>>() {}.getType());
    }

    @Override
    public void updatePackageStatus(Long id, Status status) {
        packageRepository.updateStatus(id, status);
    }
}
