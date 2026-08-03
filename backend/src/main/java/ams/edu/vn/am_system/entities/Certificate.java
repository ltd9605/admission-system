package ams.edu.vn.am_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {
    @Id
    private String id;
    @Column(name = "candidate_identitycard")
    private String candidateIdentitycard;
    @Column(name = "insuer")
    private String insuer;
    @Column(name = "code")
    private String code;
    @Column(name = "certificate_name")
    private String certificateName;
}
