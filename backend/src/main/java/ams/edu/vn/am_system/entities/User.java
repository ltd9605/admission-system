package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "role_ID")
    private String roleId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "is_active")
    private Boolean isActive;
}
