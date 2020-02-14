package io.learning.demo.service.user;

import io.learning.demo.dataaccess.user.entity.UserEntity;
import io.learning.demo.dataaccess.user.mapper.UserMapper;
import io.learning.demo.dataaccess.user.repository.UserRepository;
import io.learning.demo.model.role.Role;
import io.learning.demo.model.user.User;
import io.learning.demo.model.utils.Constant;
import io.learning.demo.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public Collection<User> findAll() {
        return userMapper.mapToBos(userRepository.findAll());
    }

    @Override
    public User save(User user) throws Exception {

        if (Objects.isNull(user)) {
            throw new IllegalArgumentException(Constant.USER_CANNOT_BE_NULL);
        }

        if (Objects.isNull(user.getUsername()) || Objects.isNull(user.getEmail())) {
            throw new Exception(Constant.USER_OR_EMAIL_CANNOT_BE_NULL);
        }

        List<UserEntity> existingUsers = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (!Objects.isNull(existingUsers) && !existingUsers.isEmpty()) {
            throw new Exception(Constant.USER_ALREADY_EXISTS);
        }

        Role role = roleService.findByName(Constant.ROLE_USER);

        if(Objects.isNull(role)) {
            throw new Exception(Constant.ROLE_DOESNT_EXISTS);
        }

        user.setRoles(Collections.singleton(role));
        UserEntity userEntity = userMapper.mapToEo(user);
        userEntity = userRepository.save(userEntity);

        return userMapper.mapToBo(userEntity);
    }

    @Override
    public User update(User user) throws Exception {


        if (Objects.isNull(user)) {
            throw new IllegalArgumentException(Constant.USER_CANNOT_BE_NULL);
        }

        Optional<UserEntity> optional = userRepository.findById(user.getId());

        if (!optional.isPresent()) {
            throw new Exception(Constant.USER_DOESNT_EXISTS);
        }

        List<UserEntity> existingUsers = userRepository.findByIdNotAndUsernameOrIdNotAndEmail(optional.get().getId(), user.getUsername(), user.getEmail());

        existingUsers.forEach(System.out::println);

        if (Objects.nonNull(existingUsers) && !existingUsers.isEmpty()) {
            throw new Exception(Constant.USER_OR_EMAIL_ALREADY_EXISTS);
        }

        //TODO : Not a good idea. This is just for tutorial purpose
        user.setId(optional.get().getId());

        UserEntity userToUpdate = userMapper.mapToEo(user);
        userToUpdate = userRepository.save(userToUpdate);
        return userMapper.mapToBo(userToUpdate);
    }

    @Override
    public void delete(Long id) {

        UserEntity user = userRepository.findById(id).orElse(null);

        if (Objects.nonNull(user)) {
            userRepository.delete(user);
        }
    }

}
