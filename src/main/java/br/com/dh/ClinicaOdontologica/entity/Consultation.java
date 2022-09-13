package br.com.dh.ClinicaOdontologica.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.*;

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

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Dentist dentist;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Client client;

  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate createdAt;

  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate updateAt;

  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate scheduledDate;

  @JsonFormat(pattern="HH:mm:ss")
  private Time scheduledTime;
}
