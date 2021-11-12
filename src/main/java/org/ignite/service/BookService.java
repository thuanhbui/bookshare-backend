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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<?> getListBooks() {
        List<?> books = bookRepository.getListBooks();
        return books;
    }

    public List<?> findBookById(String id) {
        List<?> book = bookRepository.findBookById(id);
        return book;
    }


    public List<List<?>> searchBook(String keyword) {
//        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM EBOOK WHERE title LIKE \'%" + keyword + "%\';");
//        List<List<?>> res = bookCache.query(qry.setDistributedJoins(true)).getAll();
//        if (res != null) return res;
//        throw new NotFoundException("Không tìm thấy sách");
        return null;
    }
}
