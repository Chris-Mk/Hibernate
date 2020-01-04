package com.mkolongo.residentEvil.service;

import com.mkolongo.residentEvil.domain.entities.Role;
import com.mkolongo.residentEvil.domain.entities.User;
import com.mkolongo.residentEvil.domain.models.service.UserServiceModel;
import com.mkolongo.residentEvil.domain.models.view.UserViewModel;
import com.mkolongo.residentEvil.repository.RoleRepository;
import com.mkolongo.residentEvil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        final String encodePassword = bCryptPasswordEncoder.encode(userServiceModel.getPassword());

        final User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(encodePassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        setUserAuthority(user);
        userRepository.saveAndFlush(user);

        return userServiceModel;
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    final UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);

                    Set<String> roles = new HashSet<>();
                    user.getAuthorities()
                            .forEach(role -> roles.add(role.getAuthority()));

                    userViewModel.setAuthorities(roles);
                    return userViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public UserViewModel getUserById(Long id) {
        final User user = userRepository.getOne(id);
        final Set<String> roles = user.getAuthorities()
                .stream()
                .map(Role::getAuthority)
                .collect(Collectors.toSet());

        final UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);
        userViewModel.setAuthorities(roles);

        return userViewModel;
    }

    @Override
    public void editRoles(Long id, boolean moderator, boolean admin) {
        final User user = userRepository.getOne(id);

        var roles = new HashSet<Role>();
        roles.add(roleRepository.findRoleByAuthority("ROLE_USER"));

        if (moderator) {
            roles.add(roleRepository.findRoleByAuthority("ROLE_MODERATOR"));
        }

        if (admin) {
            roles.add(roleRepository.findRoleByAuthority("ROLE_ADMIN"));
        }

        user.setAuthorities(roles);
        userRepository.saveAndFlush(user);
    }

    private void setUserAuthority(User user) {
        var roles = new HashSet<Role>();

        if (userRepository.count() == 0) {
            roles.add(roleRepository.findRoleByAuthority("ROLE_ADMIN"));
            roles.add(roleRepository.findRoleByAuthority("ROLE_MODERATOR"));
        }

        roles.add(roleRepository.findRoleByAuthority("ROLE_USER"));
        user.setAuthorities(roles);
    }
}
