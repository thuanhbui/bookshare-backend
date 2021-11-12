package org.ignite.controller;


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
    public ResponseEntity<?> getListCatalogs() {
        List<?> catas = catalogService.getListCatalogs();
        return ResponseEntity.ok(catas);
    }


//    @PutMapping("/{id}")
//    public eCatalogDto updateCatalog(@PathVariable Integer id, @RequestBody eCatalogDto catalogDto) {
//        return catalogService.updateCatalog(id, catalogDto.getName_catalog());
//    }

    @GetMapping("/{catalog_id}")
    public List<? > getCatalogById(@PathVariable int catalog_id) {
        return catalogService.findCatalogById(catalog_id);
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
