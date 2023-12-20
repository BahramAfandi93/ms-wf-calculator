package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.model.Role;
import com.construe.waterflowcalc.model.User;
import com.construe.waterflowcalc.repository.RoleRepository;
import com.construe.waterflowcalc.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Adding new role");

        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String userRole) {
        log.info("Adding role to user");
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRole(userRole).get();
        user.getRole().add(role);
    }
}
