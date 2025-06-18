package com.galiana_project.cl.galiana_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.galiana_project.cl.galiana_project.model.Asiento;
import com.galiana_project.cl.galiana_project.model.Boleta;
import com.galiana_project.cl.galiana_project.model.Pago;
import com.galiana_project.cl.galiana_project.model.Usuario;
import com.galiana_project.cl.galiana_project.repository.BoletaRepository;

@SpringBootTest
public class BoletaServiceTest {

    @Autowired 
    private BoletaService boletaService;

    @MockBean 
    private BoletaRepository boletaRepository;

    private Boleta createBoleta() {
        return new Boleta(1, new Date(), 45000, new Asiento(), new Pago(), new Usuario());
    }

    @Test
    public void testFindAll() {
        when(boletaService.findAll()).thenReturn(List.of(createBoleta()));
        List<Boleta> boletas = boletaService.findAll();
        assertNotNull(boletas);
        assertEquals(1, boletas.size());
    }

    @Test
    public void testFindById() {
        when(boletaRepository.findById(1L)).thenReturn(java.util.Optional.of(createBoleta()));
        Boleta boleta = boletaService.findById(1L);
        assertNotNull(boleta);
        assertEquals(45000, boleta.getPrecioTotal());
    }

    @Test
    public void testSave() {
        Boleta boleta = createBoleta();
        when(boletaRepository.save(boleta)).thenReturn(boleta);
        Boleta savedBoleta = boletaRepository.save(boleta);
        assertNotNull(savedBoleta);
        assertEquals(45000, savedBoleta.getPrecioTotal());
    }
    
    @Test
    public void testPatchBoleta() {
        Boleta existingBoleta = createBoleta();
        Boleta patchData = new Boleta();
        patchData.setPrecioTotal(1);

        when(boletaRepository.findById(1L)).thenReturn(java.util.Optional.of(existingBoleta));
        when(boletaRepository.save(any(Boleta.class))).thenReturn(existingBoleta);
        
        Boleta patchedBoleta = boletaService.patchBoleta(1L, patchData);
        assertNotNull(patchedBoleta);
        assertEquals(1, patchedBoleta.getPrecioTotal());
    }
    
    @Test
    public void testDeleteById() {
        doNothing().when(boletaRepository).deleteById(1L);
        boletaService.deleteById(1L);
        verify(boletaRepository, times(1)).deleteById(1L);
    }
}
