package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "scores")
@Data
public class Score {
    @Id
    private String id;
    @Column(name = "candidate_identitycard")
    private String candidateIdentitycard;
    @Column(name = "subject_code")
    private String subjectCode;
    @Column(name = "score")
    private Float score;
    @Column(name = "exam_type")
    private String examType;
}
