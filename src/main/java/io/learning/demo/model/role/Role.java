package io.learning.demo.model.role;

import io.learning.demo.model.user.User;
import lombok.*;

import java.util.Set;

@Data
public class Role {

    private Long id;
    private String name;

    //TODO : To avoid circular dependencies i comment it and of course didnt use it in the toString() method
    //private Set<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
