package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "major_combinations")
@Data
public class MajorCombination {
    @Id
    private String id;
    @Column(name = "major_code")
    private String majorCode;
    @Column(name = "combination_code")
    private String combinationCode;
    @Column(name = "deviation")
    private Float deviation;
}
