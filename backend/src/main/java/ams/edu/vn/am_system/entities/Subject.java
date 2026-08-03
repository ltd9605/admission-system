package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subjects")
@Data
public class Subject {
    @Id
    private String id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "name")
    private String name;
}
