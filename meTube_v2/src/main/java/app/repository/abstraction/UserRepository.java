package app.repository.abstraction;

import app.domain.entities.Tube;
import app.domain.entities.User;
import app.repository.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User, String> {

    User findByUsernameAndEmail(String username, String email);

    User findByUsername(String username);

    User findByEmail(String email);

    List<Tube> findAllTubes(String username);

    User findByUsernameAndPassword(String username, String password);
}
