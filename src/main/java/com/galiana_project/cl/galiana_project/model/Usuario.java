package com.galiana_project.cl.galiana_project.model;

import java.sql.Date; // importa realmente de donde importo esto?
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
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10, unique = true)
    private String rut;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 35, unique = true)
    private String mail;

    @Column(nullable = false, length = 20)
    private String contraseña;

    @Column(nullable = true)
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "tipoUsuario", nullable = false)
    private TipoUsuario tipoUsuario;

}
