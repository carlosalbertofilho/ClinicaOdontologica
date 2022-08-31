package br.com.dh.ClinicaOdontologica.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @NotBlank(message = "Dentist is mandatory to fill")
    @ManyToOne
    private Dentist dentist;

    @NotBlank(message = "Client is mandatory to fill")
    @ManyToOne
    private Client client;

    @JsonFormat(pattern="dd-MM-yyyy h:mm a")
    @DateTimeFormat(pattern = "dd-MM-yyyy h:mm a")
    private LocalDate createdAt;

    @JsonFormat(pattern="dd-MM-yyyy h:mm a")
    @DateTimeFormat(pattern = "dd-MM-yyyy h:mm a")
    private LocalDate updateAt;

    @NotBlank(message = "ScheduledTime is mandatory to fill")
    @JsonFormat(pattern="dd-MM-yyyy h:mm a")
    @DateTimeFormat(pattern = "dd-MM-yyyy h:mm a")
    private LocalDate scheduledTime;
}
