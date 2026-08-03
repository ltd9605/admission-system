package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissions")
@Data
public class Permission {
    @Id
    private String id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "permission_name")
    private String permissionName;
}
