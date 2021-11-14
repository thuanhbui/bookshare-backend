package org.ignite.controller;


import org.ignite.Entity.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<eCatalogDto> getCatalogById(@PathVariable Integer id) {
        eCatalogDto catalogDto = catalogService.findCatalogById(id);
        return ResponseEntity.ok(catalogDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<eCatalogDto>> getAllCatalog() {
        List<eCatalogDto> catalogDtos = catalogService.getListCatalogs();
        return ResponseEntity.ok(catalogDtos);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<eCatalogDto>> findByNameCatalog(@PathVariable String name) {
        List<eCatalogDto> catalogDtos = catalogService.findCatalogByName(name);
        return ResponseEntity.ok(catalogDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<eCatalogDto> updateNameCatalog(@PathVariable Integer id, @RequestBody eCatalog catalog) {
        eCatalogDto catalogDto = catalogService.updateCatalog(id, catalog);
        return ResponseEntity.ok(catalogDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<eCatalogDto> deleteCatalog(@PathVariable Integer id) {
        eCatalogDto catalogDto = catalogService.findCatalogById(id);
        catalogService.deleteCatalog(id);
        return ResponseEntity.ok(catalogDto);
    }

    @PostMapping("")
    public ResponseEntity<eCatalog> createCatalog(@RequestBody eCatalog catalog) {
        catalogService.addCatalog(catalog);
        return ResponseEntity.ok(catalog);
    }

}
