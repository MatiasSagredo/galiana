package com.galiana_project.cl.galiana_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galiana_project.cl.galiana_project.model.Boleta;
import com.galiana_project.cl.galiana_project.repository.BoletaRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoletaService {
    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> findAll() {
        return boletaRepository.findAll();
    }

    public Boleta findById(Long id) {
        return boletaRepository.findById(id).get();
    }

    public Boleta save(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public void deleteById(Long id) {
        boletaRepository.deleteById(id);
    }

    public Boleta updateBoleta(Long id, Boleta boleta) {
        Boleta boletaToUpdate = boletaRepository.findById(id).get();
        if (boletaToUpdate != null) {
            boletaToUpdate.setFechaBoleta(boleta.getFechaBoleta());
            boletaToUpdate.setPrecioTotal(boleta.getPrecioTotal());
            boletaToUpdate.setAsiento(boleta.getAsiento());
            boletaToUpdate.setUsuario(boleta.getUsuario());
            boletaToUpdate.setPago(boleta.getPago());
            return boletaRepository.save(boletaToUpdate);
        } else {
            return null;
        }
    }

    public Boleta patchBoleta(Long id, Boleta boletaParcial) {
        Boleta boletaToUpdate = boletaRepository.findById(id).get();

        if (boletaParcial.getFechaBoleta() != null) {
            boletaToUpdate.setFechaBoleta(boletaParcial.getFechaBoleta());
        }
        if (boletaParcial.getPrecioTotal() != null) {
            boletaToUpdate.setPrecioTotal(boletaParcial.getPrecioTotal());
        }
        if (boletaParcial.getAsiento() != null) {
            boletaToUpdate.setAsiento(boletaParcial.getAsiento());
        }
        if (boletaParcial.getUsuario() != null) {
            boletaToUpdate.setUsuario(boletaParcial.getUsuario());
        }
        if (boletaParcial.getPago() != null) {
            boletaToUpdate.setPago(boletaParcial.getPago());
        }

        return boletaRepository.save(boletaToUpdate);
    }
}
