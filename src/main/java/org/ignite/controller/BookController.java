package org.ignite.controller;

import org.ignite.Entity.eBook;
import org.ignite.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<List<?>> books = bookService.searchBook(name);
        return ResponseEntity.ok(books);
    }

    @GetMapping("")
    public ResponseEntity<?> getListBook() {
        List<?> books = bookService.getListBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        List<?> result = bookService.findBookById(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> createBook() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook() {
        return null;
    }
}
