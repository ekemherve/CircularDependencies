package io.learning.demo.dataaccess;

import io.learning.demo.model.user.User;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity mapToEo(User user);

    User mapToBo(UserEntity user);

    Collection<User> mapToBos(Collection<UserEntity> users);

    Collection<UserEntity> mapToEos(Collection<User> users);
}
