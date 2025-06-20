package com.galiana_project.cl.galiana_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galiana_project.cl.galiana_project.model.AsientoBoleta;
import com.galiana_project.cl.galiana_project.repository.AsientoBoletaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AsientoBoletaService {

    @Autowired
    private AsientoBoletaRepository asientoBoletaRepository;

    public List<AsientoBoleta> findAll() {
        return asientoBoletaRepository.findAll();
    }

    public AsientoBoleta findById(Long id) {
        return asientoBoletaRepository.findById(id).get();
    }

    public AsientoBoleta save(AsientoBoleta asientoBoleta) {
        return asientoBoletaRepository.save(asientoBoleta);
    }

    public void deleteById(Long id) {
        asientoBoletaRepository.deleteById(id);
    }

    public AsientoBoleta updateAsientoBoleta(Long id, AsientoBoleta asientoBoleta) {
        AsientoBoleta asientoBoletaToUpdate = asientoBoletaRepository.findById(id).get();
        if (asientoBoletaToUpdate != null) {
            asientoBoletaToUpdate.setId(asientoBoleta.getId());
            asientoBoletaToUpdate.setAsiento(asientoBoleta.getAsiento());
            asientoBoletaToUpdate.setBoleta(asientoBoleta.getBoleta());
            return asientoBoletaRepository.save(asientoBoletaToUpdate);
        } else {
            return null;
        }
    }

    public AsientoBoleta patchAsientoBoleta(Long id, AsientoBoleta asientoBoletaParcial) {
        AsientoBoleta asientoBoletaToUpdate = asientoBoletaRepository.findById(id).get();

        if (asientoBoletaToUpdate != null) {
            if (asientoBoletaParcial.getId() != null) {
                asientoBoletaToUpdate.setId(asientoBoletaParcial.getId());
            }
            if (asientoBoletaParcial.getAsiento() != null) {
                asientoBoletaToUpdate.setAsiento(asientoBoletaParcial.getAsiento());
            }
            if (asientoBoletaParcial.getBoleta() != null) {
                asientoBoletaToUpdate.setBoleta(asientoBoletaParcial.getBoleta());
            }
            return asientoBoletaRepository.save(asientoBoletaToUpdate);
        } else {
            return null;
        }
    }

}
