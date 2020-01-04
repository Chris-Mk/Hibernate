package app.repositories;

import app.domain.entities.Package;
import app.domain.entities.Status;

import java.util.List;

public interface PackageRepository extends BaseRepository<Package, Long> {

    List<Package> findAllWithStatus(Status status);
}
