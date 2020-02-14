package io.learning.demo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByUsernameOrEmail(String username, String email);

    @Query(value = "select u.* from postgres.users u where u.id<>?1 and (u.username=?2 or u.email=?3)", nativeQuery = true)
    List<UserEntity> findByIdNotAndUsernameOrIdNotAndEmail(Long id, String username, String email);
}
