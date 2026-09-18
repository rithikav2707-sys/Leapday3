package com.example.demo.Controller;

import com.example.demo.Model.Resource;
import com.example.demo.Service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public Resource addResource(@RequestBody Resource resource) {
        return service.addResource(resource);
    }

    @GetMapping
    public List<Resource> getResources() {
        return service.getResources();
    }
}
