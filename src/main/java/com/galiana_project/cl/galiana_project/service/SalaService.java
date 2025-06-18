package com.galiana_project.cl.galiana_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galiana_project.cl.galiana_project.model.Sala;
import com.galiana_project.cl.galiana_project.repository.SalaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala findById(Long id) {
        return salaRepository.findById(id).get();
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }
    
    public void deleteById(Long id) {
        salaRepository.deleteById(id);
    }

    public Sala updateSala(Long id, Sala sala) {
        Sala salaToUpdate = salaRepository.findById(id).get();
        if (salaToUpdate != null) {
            salaToUpdate.setId(sala.getId());
            salaToUpdate.setNumSala(sala.getNumSala());
            salaToUpdate.setCapacidad(sala.getCapacidad());
            salaToUpdate.setObra(sala.getObra());
            return salaRepository.save(salaToUpdate);
        } else {
            return null;
        }
    }

    public Sala patchSala(Long id, Sala salaParcial) {
        Sala salaToUpdate = salaRepository.findById(id).get();

        if (salaToUpdate != null) {
            if (salaParcial.getId() != null) {
                salaToUpdate.setId(salaParcial.getId());
            }
            if (salaParcial.getNumSala() != null) {
                salaToUpdate.setNumSala(salaParcial.getNumSala());
            }
            if (salaParcial.getCapacidad() != null) {
                salaToUpdate.setCapacidad(salaParcial.getCapacidad());
            }
            if (salaParcial.getObra() != null) {
                salaToUpdate.setObra(salaParcial.getObra());
            }
            return salaRepository.save(salaToUpdate);
        } else {
            return null;
        }
    }
}
