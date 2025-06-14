package com.galiana_project.cl.galiana_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galiana_project.cl.galiana_project.model.Region;
import com.galiana_project.cl.galiana_project.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Long id) {
        return regionRepository.findById(id).get();
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }

    public Region updateRegion(Long id, Region region) {
        Region regionToUpdate = regionRepository.findById(id).get();
        if (regionToUpdate != null) {
            regionToUpdate.setNombre(region.getNombre());
            return regionRepository.save(regionToUpdate);
        } else {
            return null;
        }
    }

    public Region patchRegion(Long id, Region regionParcial) {
        Region regionToUpdate = regionRepository.findById(id).get();

        if (regionParcial.getNombre() != null) {
            regionToUpdate.setNombre(regionParcial.getNombre());
        }

        return regionRepository.save(regionToUpdate);
    }
    
}
