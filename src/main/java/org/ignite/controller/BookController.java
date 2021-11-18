package org.ignite.controller;

import org.apache.ignite.springdata22.repository.config.Query;
import org.ignite.Entity.*;
import org.ignite.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (bookDto == null)    return (ResponseEntity<eBookDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<eBookDto>> getAllBooks() {
        List<eBookDto> bookDtos = bookService.getListBooks();
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("")
    public ResponseEntity<List<eBookDto>> findByTitle(@RequestParam (value = "title") String title) {
        List<eBookDto> bookDtos = bookService.findBookByTitle(title);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/ofCatalog")
    public ResponseEntity<List<eBookDto>> findBooksByCatalogId(@RequestParam (value = "catalogId") Integer catalogId) {
        List<eBookDto> bookDtos = bookService.getBooksByCatalogId(catalogId);
        return ResponseEntity.ok(bookDtos);
    }
    @GetMapping("/ofUser")
    public ResponseEntity<List<eBookDto>> findBooksByUserId(@RequestParam (value = "userId") Integer catalogId) {
        List<eBookDto> bookDtos = bookService.getBooksByUserId(catalogId);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/new")
    public ResponseEntity<List<eBookDto>> getListNewBooks() {
        List<eBookDto> bookDtos = bookService.getListNewBooks();
        return ResponseEntity.ok(bookDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<eBookDto> updateBookTitle(@PathVariable String id, @RequestBody eBook book) {
        eBookDto eBookDto = bookService.updateBook(id, book);
        return ResponseEntity.ok(eBookDto);
    }

    @PutMapping("/viewers/{id}")
    public ResponseEntity<eBookDto> updateViewers(@PathVariable String id) {
        eBookDto eBookDto = bookService.updateViewers(id);
        return ResponseEntity.ok(eBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<eBookDto> deleteBook(@PathVariable String id) {
        eBookDto bookDto = bookService.findBookById(id);
        if (bookDto == null)    return (ResponseEntity<eBookDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        bookService.deleteBook(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("")
    public ResponseEntity<eBookDto> createBook(@RequestBody eBook book, @RequestParam (value = "userId") Integer userId) {
        List<eBookDto> foundBook = bookService.findBookByTitle(book.getTitle().trim());
        if (foundBook.size() > 0 ) {
            return (ResponseEntity<eBookDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        eBookDto bookDto = bookService.addBook(book, userId);

        return ResponseEntity.ok(bookDto);
    }

}
