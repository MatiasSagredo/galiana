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
}
