package org.ignite.controller;

import org.ignite.Entity.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<eBookDto> getBookById(@PathVariable String id) {
        eBookDto bookDto = bookService.findBookById(id);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<eBookDto>> getAllBook() {
        List<eBookDto> bookDtos = bookService.getListBooks();
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<eBookDto>> findByTitle(@PathVariable String title) {
        List<eBookDto> bookDtos = bookService.findBookByTitle(title);
        return ResponseEntity.ok(bookDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<eBookDto> updateBookTitle(@PathVariable String id, @RequestBody eBook book) {
        eBookDto eBookDto = bookService.updateBook(id, book);
        return ResponseEntity.ok(eBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<eBookDto> deleteBook(@PathVariable String id) {
        eBookDto bookDto = bookService.findBookById(id);
        bookService.deleteBook(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("")
    public ResponseEntity<eBook> createBook(@RequestBody eBook book) {
        bookService.addBook(book);
        return ResponseEntity.ok(book);
    }

}
