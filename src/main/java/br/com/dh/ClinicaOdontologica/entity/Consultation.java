package br.com.dh.ClinicaOdontologica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Consultation 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "DentistUser", fetch = FetchType.LAZY)
    @Column(name = "dentist_id", nullable = false)
    private Long dentistId;

    @OneToMany(mappedBy = "ClientUser", fetch = FetchType.LAZY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

}
