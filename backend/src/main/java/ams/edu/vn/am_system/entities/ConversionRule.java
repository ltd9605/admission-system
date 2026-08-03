package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "conversion_rules")
@Data
public class ConversionRule {
    @Id
    private String id;
    @Column(name = "method")
    private String method;
    @Column(name = "convert_subject_code")
    private String convertSubjectCode;
    @Column(name = "convert_combination_code")
    private String convertCombinationCode;
    @Column(name = "score_a")
    private Float scoreA;
    @Column(name = "score_b")
    private Float scoreB;
    @Column(name = "converted_score_c")
    private Float convertedScoreC;
    @Column(name = "converted_score_d")
    private Float convertedScoreD;
    @Column(name = "percentile")
    private String percentile;
}
