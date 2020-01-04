package com.mkolongo.springdata.repositories;

import com.mkolongo.springdata.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
