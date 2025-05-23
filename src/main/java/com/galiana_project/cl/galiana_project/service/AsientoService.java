package com.galiana_project.cl.galiana_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galiana_project.cl.galiana_project.model.Asiento;
import com.galiana_project.cl.galiana_project.repository.AsientoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    public List<Asiento> findAll() {
        return asientoRepository.findAll();
    }

    public Asiento findById(Long id) {
        return asientoRepository.findById(id).get();
    }

    public Asiento save(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    public void deleteById(Long id) {
        asientoRepository.deleteById(id);
    }

    public Asiento updateAsiento(Long id, Asiento asiento) {
        Asiento asienToToUpdate = asientoRepository.findById(id).get();
        if (asienToToUpdate != null) {
            asienToToUpdate.setFila(asiento.getFila());
            asienToToUpdate.setNumAsiento(asiento.getNumAsiento());
            asienToToUpdate.setEstado(asiento.getEstado());
            return asientoRepository.save(asienToToUpdate);
        } else {
            return null;
        }
    }

    public Asiento patchAsiento(Long id, Asiento asientoParcial) {
        Asiento asientoToUpdate = asientoRepository.findById(id).get();

        if (asientoParcial.getFila() != null) {
            asientoToUpdate.setFila(asientoParcial.getFila());
        }
        if (asientoParcial.getNumAsiento() != null) {
            asientoToUpdate.setNumAsiento(asientoParcial.getNumAsiento());
        }
        if (asientoParcial.getEstado() != null) {
            asientoToUpdate.setEstado(asientoParcial.getEstado());
        }

        return asientoRepository.save(asientoToUpdate);
    }
}
