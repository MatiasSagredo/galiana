package com.galiana_project.cl.galiana_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galiana_project.cl.galiana_project.model.SalaTeatro;
import com.galiana_project.cl.galiana_project.repository.SalaTeatroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SalaTeatroService {

    @Autowired
    private SalaTeatroRepository obraTeatroRepository;

    public List<SalaTeatro> findAll() {
        return obraTeatroRepository.findAll();
    }

    public SalaTeatro findById(Long id) {
        return obraTeatroRepository.findById(id).get();
    }

    public SalaTeatro save(SalaTeatro obraTeatro) {
        return obraTeatroRepository.save(obraTeatro);
    }

    public void deleteById(Long id) {
        obraTeatroRepository.deleteById(id);
    }

    public SalaTeatro updateObraTeatro(Long id, SalaTeatro obraTeatro) {
        SalaTeatro obraTeatroToUpdate = obraTeatroRepository.findById(id).get();
        if (obraTeatroToUpdate != null) {
            obraTeatroToUpdate.setId(obraTeatro.getId());
            obraTeatroToUpdate.setTeatro(obraTeatro.getTeatro());
            obraTeatroToUpdate.setSala(obraTeatro.getSala());
            return obraTeatroRepository.save(obraTeatroToUpdate);
        } else {
            return null;
        }
    }

    public SalaTeatro patchObraTeatro(Long id, SalaTeatro obraParcial) {
        SalaTeatro obraToUpdate = obraTeatroRepository.findById(id).get();

        if (obraToUpdate != null) {
            if (obraParcial.getId() != null) {
                obraToUpdate.setId(obraParcial.getId());
            }
            if (obraParcial.getTeatro() != null) {
                obraToUpdate.setTeatro(obraParcial.getTeatro());
            }
            if (obraParcial.getSala() != null) {
                obraToUpdate.setSala(obraParcial.getSala());
            }
            return obraTeatroRepository.save(obraToUpdate);
        } else {
            return null;
        }
    }

}
