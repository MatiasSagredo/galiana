package com.galiana_project.cl.galiana_project.repository;

import org.springframework.stereotype.Repository;

import com.galiana_project.cl.galiana_project.model.Ciudad;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

}
