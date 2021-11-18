package org.ignite.controller;


import org.ignite.Entity.*;
import org.ignite.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
//        eCatalogDto catalogDto = catalogService.findCatalogById(id);
        eCatalogDto catalogDto = catalogService.findCatalogByKey(id);
        return ResponseEntity.ok(catalogDto);
    }



    @GetMapping("/all")
    public ResponseEntity<List<eCatalogDto>> getAllCatalogs() {
        List<eCatalogDto> catalogDtos = catalogService.getListCatalogs();
        return ResponseEntity.ok(catalogDtos);
    }

    @GetMapping("")
    public ResponseEntity<List<eCatalogDto>> findByNameCatalog(@RequestParam (value = "nameCatalog") String nameCatalog) {
        List<eCatalogDto> catalogDtos = catalogService.findCatalogByNameCatalog(nameCatalog);
        return ResponseEntity.ok(catalogDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<eCatalogDto> updateNameCatalog(@PathVariable Integer id, @RequestBody eCatalog catalog) {
        eCatalogDto catalogDto = catalogService.updateCatalog(id, catalog);
        return ResponseEntity.ok(catalogDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<eCatalogDto> deleteCatalog(@PathVariable Integer id) {
        eCatalogDto catalogDto = catalogService.findCatalogByKey(id);
        catalogService.deleteCatalog(id);
        return ResponseEntity.ok(catalogDto);
    }

    @PostMapping("")
    public ResponseEntity<eCatalogDto> createCatalog(@RequestBody eCatalog catalog) {
        List<eCatalogDto> foundCata = catalogService.findCatalogByNameCatalog(catalog.getNameCatalog().trim());
        if (foundCata.size() > 0 ) {
            return (ResponseEntity<eCatalogDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        eCatalogDto catalogDto = catalogService.addCatalog(catalog);
        return ResponseEntity.ok(catalogDto);
    }

}
