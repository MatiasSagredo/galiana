package com.galiana_project.cl.galiana_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.galiana_project.cl.galiana_project.model.Asiento;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {

}
