package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.Role;

import java.util.List;

public interface RoleService {
Role addRole(Role role);
List<Role> read();
    Role getRoleById(Long id);
    void deleteRole(Long id);

}
