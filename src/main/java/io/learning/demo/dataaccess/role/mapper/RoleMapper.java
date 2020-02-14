package io.learning.demo.dataaccess.role.mapper;

import io.learning.demo.dataaccess.role.entity.RoleEntity;
import io.learning.demo.model.role.Role;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleEntity mapToEo(Role role);

    Role mapToBo(RoleEntity role);

    Collection<Role> mapToBos(Collection<RoleEntity> roles);

    Collection<RoleEntity> mapToEos(Collection<Role> roles);
}
