package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
@Data
public class Candidate {
    @Id
    private String id;
    @Column(name = "identitycard", unique = true)
    private String identitycard;
    @Column(name = "register_number")
    private String registerNumber;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "birthday")
    private LocalDateTime birthday;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "gender")
    private String gender;
    @Column(name = "enrollment_year")
    private Integer enrollmentYear;
    @Column(name = "priority_point")
    private Float priorityPoint;
    @Column(name = "folks")
    private String folks;
    @Column(name = "birthplace")
    private String birthplace;
    @Column(name = "priority_area")
    private String priorityArea;
    @Column(name = "priority_target")
    private String priorityTarget;
    @Column(name = "status")
    private String status;
}
