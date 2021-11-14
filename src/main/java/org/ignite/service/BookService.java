package org.ignite.service;


import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Dao.BookRepository;
import org.ignite.Entity.*;
import org.ignite.config.IgniteConfig;
import org.ignite.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<eBookDto> findBookByTitle(String title) {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByTitle(title);
        List<eBookDto> bookDtos = new ArrayList<>();
        for(Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }

    public eBookDto findBookById(String book_id) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(book_id);
        return new eBookDto(entry.getKey(), entry.getValue());
    }

    public List<eBookDto> getListBooks() {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.getListBooks();
        List<eBookDto> bookDtos = new ArrayList<>();
        for(Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }

    public eBookDto updateBook(String bookId, eBook book) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(bookId);
        entry.getValue().setTitle(book.getTitle());
        entry.getValue().setDescription(book.getDescription());
        entry.getValue().setImageLink(book.getImageLink());
        entry.getValue().setFileLink(book.getFileLink());
        entry.getValue().setLanguage(book.getLanguage());
        entry.getValue().setReleaseYear(book.getReleaseYear());
        entry.getValue().setLastUpdate(book.getLastUpdate());
        entry.getValue().setViewers(book.getViewers());
        bookRepository.save(entry.getKey(), entry.getValue());
        return new eBookDto(entry.getKey(), entry.getValue());
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }

    public void addBook(eBook book) {
        bookRepository.save(book);
    }
}
