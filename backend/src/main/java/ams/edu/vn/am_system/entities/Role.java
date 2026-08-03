package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    private String id;
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private java.util.List<Permission> permissions;
}
