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
    public ResponseEntity<?> getCatalogById(@PathVariable Integer id) {
        eCatalogDto catalogDto = catalogService.findCatalogByKey(id);
        if (catalogDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có danh mục này trong hệ thống!");
        return ResponseEntity.ok(catalogDto);
    }



    @GetMapping("/all")
    public ResponseEntity<?> getAllCatalogs() {
        List<eCatalogDto> catalogDtos = catalogService.getListCatalogs();
        if (catalogDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hiện tại, không có danh mục nào trong hệ thống :(");
        return ResponseEntity.ok(catalogDtos);
    }

    @GetMapping("")
    public ResponseEntity<?> findByNameCatalog(@RequestParam (value = "nameCatalog") String nameCatalog) {
        List<eCatalogDto> catalogDtos = catalogService.findCatalogByNameCatalog(nameCatalog);
        if (catalogDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tên danh mục này");
        return ResponseEntity.ok(catalogDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNameCatalog(@PathVariable Integer id, @RequestBody eCatalog catalog) {
        eCatalogDto catalogDto = catalogService.findCatalogByKey(id);
        if (catalogDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có danh mục này trong hệ thống!");
        eCatalogDto catalogDto1 = catalogService.updateCatalog(id, catalog);
        return ResponseEntity.ok(catalogDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatalog(@PathVariable Integer id) {
        eCatalogDto catalogDto = catalogService.findCatalogByKey(id);
        if (catalogDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có danh mục này trong hệ thống!");
        catalogService.deleteCatalog(id);
        return ResponseEntity.ok(catalogDto);
    }

    @PostMapping("")
    public ResponseEntity<?> createCatalog(@RequestBody eCatalog catalog) {
        List<eCatalogDto> foundCata = catalogService.findCatalogByNameCatalog(catalog.getNameCatalog().trim());
        if (foundCata.size() > 0 ) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đã có danh mục này trong hệ thống");
        }
        eCatalogDto catalogDto = catalogService.addCatalog(catalog);
        return ResponseEntity.ok(catalogDto);
    }

}
