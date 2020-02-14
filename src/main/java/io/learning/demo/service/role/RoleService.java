package io.learning.demo.service.role;

import io.learning.demo.model.role.Role;
import io.learning.demo.service.core.GenericService;

public interface RoleService extends GenericService<Role, Long> {

    Role findByName(String name);
}
