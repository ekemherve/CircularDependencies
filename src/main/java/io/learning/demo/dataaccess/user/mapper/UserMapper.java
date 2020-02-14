package io.learning.demo.dataaccess.user.mapper;

import io.learning.demo.dataaccess.role.mapper.RoleMapper;
import io.learning.demo.dataaccess.user.entity.UserEntity;
import io.learning.demo.model.user.User;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserEntity mapToEo(User user);

    User mapToBo(UserEntity user);

    Collection<User> mapToBos(Collection<UserEntity> users);

    Collection<UserEntity> mapToEos(Collection<User> users);
}
