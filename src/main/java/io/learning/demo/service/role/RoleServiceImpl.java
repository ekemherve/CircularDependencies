package io.learning.demo.service.role;

import io.learning.demo.dataaccess.role.entity.RoleEntity;
import io.learning.demo.dataaccess.role.mapper.RoleMapper;
import io.learning.demo.dataaccess.role.repository.RoleRepository;
import io.learning.demo.dataaccess.user.UserEntity;
import io.learning.demo.model.role.Role;
import io.learning.demo.model.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<Role> findAll() {
        return roleMapper.mapToBos(roleRepository.findAll());
    }

    @Override
    public Role save(Role role) throws Exception {

        if (Objects.isNull(role)) {
            throw new IllegalArgumentException(Constant.ROLE_CANNOT_BE_NULL);
        }

        if (Objects.isNull(role.getName())) {
            throw new Exception(Constant.ROLE_NAME_CANNOT_BE_NULL);
        }

        if (Objects.nonNull(roleRepository.findByName(role.getName()))) {
            throw new Exception(Constant.USER_ALREADY_EXISTS);
        }


        RoleEntity roleEntity = roleMapper.mapToEo(role);
        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.mapToBo(roleEntity);
    }

    @Override
    public Role update(Role role) throws Exception {

        if (Objects.isNull(role)) {
            throw new IllegalArgumentException(Constant.ROLE_CANNOT_BE_NULL);
        }

        if (Objects.isNull(role.getName())) {
            throw new Exception(Constant.ROLE_NAME_CANNOT_BE_NULL);
        }

        RoleEntity roleFromDatabase = roleRepository.findById(role.getId()).orElseThrow(() -> new Exception(Constant.ROLE_DOESNT_EXISTS));

        if(Objects.nonNull(roleRepository.findByIdNotAndName(roleFromDatabase.getId(), role.getName()))){
            throw new Exception(Constant.ROLE_ALREADY_EXISTS);
        }

        role.setId(roleFromDatabase.getId());

        RoleEntity roleEntity = roleMapper.mapToEo(role);
        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.mapToBo(roleEntity);
    }

    @Override
    public void delete(Long id) {

        RoleEntity roleFromDatabase = roleRepository.findById(id).orElse(null);
        if(Objects.nonNull(roleFromDatabase)){
            roleRepository.delete(roleFromDatabase);
        }
    }
}
