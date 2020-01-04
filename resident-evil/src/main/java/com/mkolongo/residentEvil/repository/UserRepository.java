package com.mkolongo.residentEvil.repository;

import com.mkolongo.residentEvil.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
