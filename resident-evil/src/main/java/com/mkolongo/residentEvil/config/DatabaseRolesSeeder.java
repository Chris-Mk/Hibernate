package com.mkolongo.residentEvil.config;

import com.mkolongo.residentEvil.domain.entities.Role;
import com.mkolongo.residentEvil.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DatabaseRolesSeeder {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void seedRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setAuthority("ROLE_ADMIN");

            Role moderator = new Role();
            moderator.setAuthority("ROLE_MODERATOR");

            Role user = new Role();
            user.setAuthority("ROLE_USER");

            roleRepository.saveAndFlush(admin);
            roleRepository.saveAndFlush(moderator);
            roleRepository.saveAndFlush(user);
        }
    }
}
