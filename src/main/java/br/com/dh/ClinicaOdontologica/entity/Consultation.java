package br.com.dh.ClinicaOdontologica.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Consultation  implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Dentist dentist;
    
    @ManyToOne
    private Client user;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate updateAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate scheduledTime;
}
