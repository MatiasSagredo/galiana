package com.galiana_project.cl.galiana_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.galiana_project.cl.galiana_project.model.Asiento;
import com.galiana_project.cl.galiana_project.model.Sala;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {

    void deleteBySala(Sala sala);
}
