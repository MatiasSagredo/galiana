package com.galiana_project.cl.galiana_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galiana_project.cl.galiana_project.model.Teatro;
import com.galiana_project.cl.galiana_project.repository.TeatroRepository;

@Service
public class TeatroService {

    @Autowired
    private TeatroRepository teatroRepository;

    public List<Teatro> findAll() {
        return teatroRepository.findAll();
    }

    public Teatro findById(Long id) {
        return teatroRepository.findById(id).get();
    }

    public Teatro save(Teatro teatro) {
        return teatroRepository.save(teatro);
    }

    public void deleteById(Long id) {
        teatroRepository.deleteById(id);
    }

    public Teatro updateTeatro(Long id, Teatro teatro) {
        Teatro teatroToUpdate = teatroRepository.findById(id).get();
        if (teatroToUpdate != null) {
            teatroToUpdate.setNombre(teatro.getNombre());
            teatroToUpdate.setDireccion(teatro.getDireccion());
            teatroToUpdate.setContacto(teatro.getContacto());
            return teatroRepository.save(teatroToUpdate);
        } else {
            return null;
        }
    }

    public Teatro patchTeatro(Long id, Teatro teatroParcial) {
        Teatro teatroToUpdate = teatroRepository.findById(id).get();

        if (teatroParcial.getNombre() != null) {
            teatroToUpdate.setNombre(teatroParcial.getNombre());
        }
        if (teatroParcial.getDireccion() != null) {
            teatroToUpdate.setDireccion(teatroParcial.getDireccion());
        }
        if (teatroParcial.getContacto() != null) {
            teatroToUpdate.setContacto(teatroParcial.getContacto());
        }

        return teatroRepository.save(teatroToUpdate);
    }

    public List<Teatro> findTeatrosFromComuna(Long id) {
        return teatroRepository.findTeatrosFromComuna(id);
    }
}
