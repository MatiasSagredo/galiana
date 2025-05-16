package com.galiana_project.cl.galiana.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false, length = 20) // sera unique? seran strings??
    private String horario;

    @Column(nullable = false, length = 20)
    private Date fechaInicio;

    @Column(nullable = false, length = 20)
    private Date fechaTermino;

    @Column(nullable = false, length = 7)
    private Integer precio; // me dijeron que cambiara esta shit para el service

    @Column(nullable = false, length = 70)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "teatro_id", nullable = false)
    private Teatro teatro;
}
