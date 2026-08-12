package ams.edu.vn.am_system.dtos;

import java.time.LocalDateTime;

import ams.edu.vn.am_system.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String userName;
    private String fullName;
    private String email;
    private String roleId;
    private LocalDateTime createdAt;
    private Boolean isActive;

    public UserDTO(User userEntity) {
        this.id = userEntity.getId();
        this.userName = userEntity.getUserName();
        this.fullName = userEntity.getFullName();
        this.email = userEntity.getEmail();
        this.roleId = userEntity.getRoleId();
        this.createdAt = userEntity.getCreatedAt();
        this.isActive = userEntity.getIsActive();
    }

    public UserDTO(String id, String userName, String fullName, String email, String roleId, LocalDateTime createdAt,
            Boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }
}
