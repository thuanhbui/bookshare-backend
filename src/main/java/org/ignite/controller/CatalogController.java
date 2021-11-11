package org.ignite.controller;


import org.ignite.Entity.eCatalog;
import org.ignite.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/catalogs")
@RestController
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/search")
    public ResponseEntity<?> searchCatalog(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<List<?>> catas = catalogService.searchCatalog(name);
        return ResponseEntity.ok(catas);
    }

    @GetMapping("")
    public ResponseEntity<?> getListCatalog() {
        List<List<?>> catas = catalogService.getListCatalog();
        return ResponseEntity.ok(catas);
    }



    @PostMapping("")
    public ResponseEntity<?> createCatalog() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatalog() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatalog() {
        return null;
    }
}
