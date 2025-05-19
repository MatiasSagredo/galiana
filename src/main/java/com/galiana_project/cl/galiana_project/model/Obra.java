package com.galiana_project.cl.galiana_project.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "obra")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(nullable = false, length = 20)
    private Time horario;

    @Column(nullable = false, length = 20)
    private Date fechaInicio;

    @Column(nullable = false, length = 20)
    private Date fechaTermino;

    @Column(nullable = false, length = 7)
    private Integer precio;

    @Column(nullable = false, length = 70)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "obraTeatro", nullable = false)
    private ObraTeatro obraTeatro;

    @ManyToOne
    @JoinColumn(name = "director", nullable = false)
    private Director director;
}
