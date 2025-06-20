package com.galiana_project.cl.galiana_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galiana_project.cl.galiana_project.model.Boleta;
import com.galiana_project.cl.galiana_project.model.Pago;
import com.galiana_project.cl.galiana_project.model.Usuario;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    void deleteByPago(Pago pago);

    void deleteByUsuario(Usuario usuario);

}
