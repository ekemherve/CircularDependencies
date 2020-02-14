package io.learning.demo.service.user;

import io.learning.demo.dataaccess.user.UserEntity;
import io.learning.demo.dataaccess.user.UserMapper;
import io.learning.demo.dataaccess.user.UserRepository;
import io.learning.demo.model.user.User;
import io.learning.demo.model.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

        if (!existingUsers.isEmpty()) {
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
