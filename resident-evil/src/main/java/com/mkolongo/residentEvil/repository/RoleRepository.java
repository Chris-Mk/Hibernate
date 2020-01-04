package com.mkolongo.residentEvil.repository;

import com.mkolongo.residentEvil.domain.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    Role findRoleByAuthority(String authority);
}
