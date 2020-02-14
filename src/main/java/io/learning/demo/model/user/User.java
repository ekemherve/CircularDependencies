package io.learning.demo.model.user;


import io.learning.demo.model.role.Role;
import lombok.Data;

import java.util.Set;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
}
