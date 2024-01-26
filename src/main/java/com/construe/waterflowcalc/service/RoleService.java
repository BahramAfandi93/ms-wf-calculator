package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role saveRole(Role role);
    void addRoleToUser(String username, String role);
}
