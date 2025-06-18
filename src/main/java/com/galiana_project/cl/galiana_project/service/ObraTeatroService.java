package com.galiana_project.cl.galiana_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galiana_project.cl.galiana_project.model.ObraTeatro;
import com.galiana_project.cl.galiana_project.repository.ObraTeatroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ObraTeatroService {

    @Autowired
    private ObraTeatroRepository obraTeatroRepository;

    public List<ObraTeatro> findAll() {
        return obraTeatroRepository.findAll();
    }

    public ObraTeatro findById(Long id) {
        return obraTeatroRepository.findById(id).get();
    }

    public ObraTeatro save(ObraTeatro obraTeatro) {
        return obraTeatroRepository.save(obraTeatro);
    }

    public void deleteById(Long id) {
        obraTeatroRepository.deleteById(id);
    }

    public ObraTeatro updateObraTeatro(Long id, ObraTeatro obraTeatro) {
        ObraTeatro obraToUpdate = obraTeatroRepository.findById(id).get();
        if (obraToUpdate != null) {
            obraToUpdate.setId(obraTeatro.getId());
            obraToUpdate.setTeatro(obraTeatro.getTeatro());
            return obraTeatroRepository.save(obraToUpdate);
        } else {
            return null;
        }
    }


    public ObraTeatro patchObraTeatro(Long id, ObraTeatro obraParcial) {
        ObraTeatro obraToUpdate = obraTeatroRepository.findById(id).get();

        if (obraToUpdate != null) {
            if (obraParcial.getId() != null) {
                obraToUpdate.setId(obraParcial.getId());
            }
            if (obraParcial.getTeatro() != null) {
                obraToUpdate.setTeatro(obraParcial.getTeatro());
            }
            return obraTeatroRepository.save(obraToUpdate);
        } else {
            return null;
        }
    }

    

}
