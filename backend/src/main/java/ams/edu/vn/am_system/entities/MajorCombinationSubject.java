package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "major_combination_subject")
@Data
public class MajorCombinationSubject {
    @Id
    private String id;
    @Column(name = "major_combinations_id")
    private String majorCombinationsId;
    @Column(name = "subject_code")
    private String subjectCode;
    @Column(name = "weight")
    private Float weight;
}
