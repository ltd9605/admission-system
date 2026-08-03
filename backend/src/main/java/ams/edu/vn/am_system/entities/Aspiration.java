package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aspirations")
@Data
public class Aspiration {
    @Id
    private String id;
    @Column(name = "candidate_identitycard")
    private String candidateIdentitycard;
    @Column(name = "priority_number")
    private Integer priorityNumber;
    @Column(name = "major_code")
    private String majorCode;
    @Column(name = "combination_code")
    private String combinationCode;
    @Column(name = "calculated_admission_score")
    private Float calculatedAdmissionScore;
    @Column(name = "admission_method")
    private String admissionMethod;
    @Column(name = "status")
    private String status;
    @Column(name = "reason")
    private String reason;
}
