package com.galiana_project.cl.galiana_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galiana_project.cl.galiana_project.model.Ciudad;
import com.galiana_project.cl.galiana_project.repository.CiudadRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    public Ciudad findById(Long id) {
        return ciudadRepository.findById(id).get();
    }

    public Ciudad save(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public void deleteById(Long id) {
        ciudadRepository.deleteById(id);
    }

    public Ciudad updateCiudad(Long id, Ciudad ciudad) {
        Ciudad ciudadToUpdate = ciudadRepository.findById(id).get();
        if (ciudadToUpdate != null) {
            ciudadToUpdate.setNombre(ciudad.getNombre());
            ciudadToUpdate.setRegion(ciudad.getRegion());
            return ciudadRepository.save(ciudadToUpdate);
        } else {
            return null;
        }
    }

    public Ciudad patchCiudad(Long id, Ciudad ciudadParcial) {
        Ciudad ciudadToUpdate = ciudadRepository.findById(id).get();

        if (ciudadParcial.getNombre() != null) {
            ciudadToUpdate.setNombre(ciudadParcial.getNombre());
        }
        if (ciudadParcial.getRegion() != null) {
            ciudadToUpdate.setRegion(ciudadParcial.getRegion());
        }

        return ciudadRepository.save(ciudadToUpdate);
    }
}