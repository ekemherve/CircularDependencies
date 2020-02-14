package io.learning.demo.dataaccess.role.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
