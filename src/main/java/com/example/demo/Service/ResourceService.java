package com.example.demo.Service;

import com.example.demo.Model.Resource;
import com.example.demo.Repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public Resource addResource(Resource resource) {
        return repository.save(resource);
    }

    public List<Resource> getResources() {
        return repository.findAll();
    }
}