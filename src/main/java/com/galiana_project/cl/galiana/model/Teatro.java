package com.galiana_project.cl.galiana.model;

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
@Table(name = "teatro")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teatro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String nombre;  

    @Column(nullable = false, length = 50)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String contacto;
    @ManyToOne
    @JoinColumn(name = "comuna_id",nullable = false)
    private Comuna comuna;
}
