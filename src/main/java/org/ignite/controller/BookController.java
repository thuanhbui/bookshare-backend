package org.ignite.controller;

import org.apache.commons.lang.ObjectUtils;
import org.apache.ignite.springdata22.repository.config.Query;
import org.ignite.Entity.*;
import org.ignite.service.BookService;
import org.ignite.service.CatalogService;
import org.ignite.service.ImageStorageService;
import org.ignite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ImageStorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    private CatalogService catalogService;



    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id, @RequestParam (value = "userId") Integer userId) {
        eBookDto bookDto = bookService.checkLike(id, userId);
        if (bookDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có mã sách này trong hệ thống");
        eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
        bookDto.setCatalogName(catalogDto.getNameCatalog());
        UserDto userDto = userService.findUserById(bookDto.getUserId());
        bookDto.setUserName(userDto.getUsername());
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/toggleLike")
    public ResponseEntity<?> toggleLike(@RequestParam (value = "userId") Integer userId,
                                        @RequestParam (value = "bookId") String bookId) {
        if (userId == null || bookId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserId hoặc BookId đang rỗng");
        }
        LikeDto likeDto = bookService.toggleLike(userId, bookId);
        return ResponseEntity.ok(likeDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        List<eBookDto> bookDtos = bookService.getListBooks();
        if (bookDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hiện tại, không có sách trong hệ thống");
        for (eBookDto bookDto : bookDtos) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
            bookDto.setCatalogName(catalogDto.getNameCatalog());
            UserDto userDto = userService.findUserById(bookDto.getUserId());
            bookDto.setUserName(userDto.getUsername());
        }
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("")
    public ResponseEntity<?> findByTitle(@RequestParam (value = "title") String title) {
        System.out.println(title);
        List<eBookDto> bookDtos = bookService.findBookByTitle(title);
        if (bookDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sách trong hệ thống");
        for (eBookDto bookDto : bookDtos) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
            bookDto.setCatalogName(catalogDto.getNameCatalog());
            UserDto userDto = userService.findUserById(bookDto.getUserId());
            bookDto.setUserName(userDto.getUsername());
        }
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/ofCatalog")
    public ResponseEntity<?> findBooksByCatalogId(@RequestParam (value = "catalogId") Integer catalogId) {
        List<eBookDto> bookDtos = bookService.getBooksByCatalogId(catalogId);
        if (bookDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sách của danh mục này trong hệ thống");
        return ResponseEntity.ok(bookDtos);
    }
    @GetMapping("/ofUser")
    public ResponseEntity<?> findBooksByUserId(@RequestParam (value = "userId") Integer userId,
                                               @RequestParam (value =  "catalogId") Integer catalogId,
                                               @RequestParam (value = "search") String keyword) {
        List<eBookDto> bookDtos = bookService.findBookByKeyUserCata(keyword, userId, catalogId);
        if (bookDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có sách của người dùng này trong hệ thống");
        for (eBookDto bookDto : bookDtos) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
            bookDto.setCatalogName(catalogDto.getNameCatalog());
            UserDto userDto = userService.findUserById(bookDto.getUserId());
            bookDto.setUserName(userDto.getUsername());
        }
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/new")
    public ResponseEntity<?> getListNewBooks() {
        List<eBookDto> bookDtos = bookService.getListNewBooks();
        if (bookDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hiện tại, không có sách trong hệ thống");
        for (eBookDto bookDto : bookDtos) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
            bookDto.setCatalogName(catalogDto.getNameCatalog());
            UserDto userDto = userService.findUserById(bookDto.getUserId());
            bookDto.setUserName(userDto.getUsername());
        }
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/top10/{catalogId}")
    public ResponseEntity<?> getTop10Books(@PathVariable (value = "catalogId") Integer catalogId) {
        List<eBookDto> bookDtos = bookService.getTop10(catalogId);
        if (bookDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hiện tại, không có sách của danh mục này trong hệ thống");
        for (eBookDto bookDto : bookDtos) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
            bookDto.setCatalogName(catalogDto.getNameCatalog());
            UserDto userDto = userService.findUserById(bookDto.getUserId());
            bookDto.setUserName(userDto.getUsername());
        }
        return ResponseEntity.ok(bookDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookTitle(@PathVariable String id, @ModelAttribute("book") eBook book) {
        eBookDto bookDto = bookService.findBookById(id);
        if (bookDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có mã sách này trong hệ thống");
        if (!book.getImgMulti().isEmpty())  book.setImageLink(storageService.storeFile(book.getImgMulti()));
        if (!book.getFileMulti().isEmpty()) book.setFileLink(storageService.storeFile(book.getFileMulti()));

        eBookDto eBookDto = bookService.updateBook(id, book);

        eCatalogDto catalogDto = catalogService.findCatalogByKey(eBookDto.getCatalogId());
        eBookDto.setCatalogName(catalogDto.getNameCatalog());
        UserDto userDto = userService.findUserById(eBookDto.getUserId());
        eBookDto.setUserName(userDto.getUsername());

        return ResponseEntity.ok(eBookDto);
    }

    @PutMapping("/viewers/{id}")
    public ResponseEntity<?> updateLikes(@PathVariable String id) {
        eBookDto bookDto = bookService.findBookById(id);
        if (bookDto == null)
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có mã sách này trong hệ thống");
        eBookDto eBookDto = bookService.increaseLikes(id);
        if (bookDto.getCatalogId() != null) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(eBookDto.getCatalogId());
            eBookDto.setCatalogName(catalogDto.getNameCatalog());
        }
        UserDto userDto = userService.findUserById(eBookDto.getUserId());
        eBookDto.setUserName(userDto.getUsername());
        return ResponseEntity.ok(eBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        eBookDto bookDto = bookService.findBookById(id);
        if (bookDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có mã sách này trong hệ thống");
        bookService.deleteBook(id);
        eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
        bookDto.setCatalogName(catalogDto.getNameCatalog());
        UserDto userDto = userService.findUserById(bookDto.getUserId());
        bookDto.setUserName(userDto.getUsername());
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("")
    public ResponseEntity<?> createBook(@ModelAttribute("book") eBook book, @RequestParam (value = "userId") Integer userId) {
        List<eBookDto> foundBook = bookService.findBookByTitle(book.getTitle().trim());
        if (foundBook.size() > 0 ) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đã có sách cùng tiêu đề trong hệ thống");
        }
        if (!book.getImgMulti().isEmpty())  book.setImageLink(storageService.storeFile(book.getImgMulti()));
        if (!book.getFileMulti().isEmpty()) book.setFileLink(storageService.storeFile(book.getFileMulti()));
        eBookDto bookDto = bookService.addBook(book, userId);
        if (bookDto.getCatalogId() != null) {
            eCatalogDto catalogDto = catalogService.findCatalogByKey(bookDto.getCatalogId());
            bookDto.setCatalogName(catalogDto.getNameCatalog());
        }
        UserDto userDto = userService.findUserById(bookDto.getUserId());
        bookDto.setUserName(userDto.getUsername());
        return ResponseEntity.ok(bookDto);
    }

}
