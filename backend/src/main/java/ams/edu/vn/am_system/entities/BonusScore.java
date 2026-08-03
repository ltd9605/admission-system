package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bonus_scores")
@Data
public class BonusScore {
    @Id
    private String id;
    @Column(name = "candidate_identitycard")
    private String candidateIdentitycard;
    @Column(name = "type")
    private String type;
    @Column(name = "score")
    private Float score;
    @Column(name = "convert_point")
    private Float convertPoint;
}
