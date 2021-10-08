package com.works.dtos;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.RoleRepository;
import com.works.repositories.UserRepository;
import com.works.utils.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterDto {

    final UserService userService;
    final RoleRepository roleRepository;

    public RegisterDto(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    public Map<String, Object> register(User us , String roleIDS) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {

            long roleID = Long.parseLong(roleIDS);
            us.setEnabled(true);
            us.setTokenExpired(true);

            Role role = roleRepository.findById(roleID).get();

            List<Role> roles = new ArrayList<>();
            roles.add(role);

            us.setRoles(roles);

            User usrr = userService.register(us);
            hm.put("status", true);
            hm.put("result", usrr);

        }catch (AuthenticationException | javax.naming.AuthenticationException ex ) {}
        hm.put("status", false);
        return hm;
    }

}
