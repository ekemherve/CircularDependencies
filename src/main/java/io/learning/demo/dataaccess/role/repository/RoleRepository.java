package io.learning.demo.dataaccess.role.repository;

import io.learning.demo.dataaccess.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    RoleEntity findByIdNotAndName(Long id, String name);
}
