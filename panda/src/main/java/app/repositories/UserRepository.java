package app.repositories;

import app.domain.entities.User;

public interface UserRepository extends BaseRepository<User, Long> {

    User findByUsername(String username);
}
