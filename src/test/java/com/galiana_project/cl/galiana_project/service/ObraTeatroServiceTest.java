package com.galiana_project.cl.galiana_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.galiana_project.cl.galiana_project.model.ObraTeatro;
import com.galiana_project.cl.galiana_project.model.Teatro;
import com.galiana_project.cl.galiana_project.repository.ObraTeatroRepository;

@SpringBootTest
public class ObraTeatroServiceTest {

    @Autowired 
    private ObraTeatroService obraTeatroService;

    @MockBean 
    private ObraTeatroRepository obraTeatroRepository;

    private ObraTeatro createObraTeatro() {
        return new ObraTeatro(1, new Teatro());
    }

    @Test
    public void testFindAll() {
        when(obraTeatroService.findAll()).thenReturn(List.of(createObraTeatro()));
        List<ObraTeatro> obrasTeatro = obraTeatroService.findAll();
        assertNotNull(obrasTeatro);
        assertEquals(1, obrasTeatro.size());
    }

    @Test
    public void testFindById() {
        when(obraTeatroRepository.findById(1L)).thenReturn(java.util.Optional.of(createObraTeatro()));
        ObraTeatro obraTeatro = obraTeatroService.findById(1L);
        assertNotNull(obraTeatro);
        assertEquals(1, obraTeatro.getId());
    }


    @Test
    public void testSave() {
        ObraTeatro obraTeatro = createObraTeatro();
        when(obraTeatroRepository.save(obraTeatro)).thenReturn(obraTeatro);
        ObraTeatro savedObraTeatro = obraTeatroRepository.save(obraTeatro);
        assertNotNull(savedObraTeatro);
        assertEquals(1, savedObraTeatro.getId());
    }
    
    @Test
    public void testPatchObraTeatro() {
        ObraTeatro existingObraTeatro = createObraTeatro();
        ObraTeatro patchData = new ObraTeatro();
        patchData.setId(2);

        when(obraTeatroRepository.findById(1L)).thenReturn(java.util.Optional.of(existingObraTeatro));
        when(obraTeatroRepository.save(any(ObraTeatro.class))).thenReturn(existingObraTeatro);
        
        ObraTeatro patchedObraTeatro = obraTeatroService.patchObraTeatro(1L, patchData);
        assertNotNull(patchedObraTeatro);
        assertEquals(2, patchedObraTeatro.getId());
    }
    
    @Test
    public void testDeleteById() {
        doNothing().when(obraTeatroRepository).deleteById(1L);
        obraTeatroService.deleteById(1L);
        verify(obraTeatroRepository, times(1)).deleteById(1L);
    }
}
