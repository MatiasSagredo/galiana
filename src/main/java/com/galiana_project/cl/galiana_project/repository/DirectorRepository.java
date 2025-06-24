package com.galiana_project.cl.galiana_project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.galiana_project.cl.galiana_project.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    List<Director> findByNombresContainingAndFechaNacimiento(String nombres, Date fechaNacimiento);

    List<Director> findByNombresAndFechaNacimiento(String nombres, Date fechaNacimiento);
}
