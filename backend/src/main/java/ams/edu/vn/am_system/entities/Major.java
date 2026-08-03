package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "majors")
@Data
public class Major {
    @Id
    private String id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "quota")
    private Integer quota;
    @Column(name = "minimum_score")
    private Float minimumScore;
    @Column(name = "admission_score")
    private Float admissionScore;
    @Column(name = "is_closed")
    private Boolean isClosed;
}
