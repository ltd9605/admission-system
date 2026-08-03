package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "combinations")
@Data
public class Combination {
    @Id
    private String id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
        name = "subject_combinations",
        joinColumns = @JoinColumn(name = "combination_code", referencedColumnName = "code"),
        inverseJoinColumns = @JoinColumn(name = "subject_code", referencedColumnName = "code")
    )
    private java.util.List<Subject> subjects;
}
