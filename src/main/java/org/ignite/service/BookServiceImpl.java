package org.ignite.service;


import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Entity.*;
import org.ignite.config.IgniteConfig;
import org.ignite.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BookServiceImpl implements BookService{

    @Autowired
    private Ignite ignite;

    IgniteCache<eBookKey, eBook> bookCache = ignite.cache("eBook");
    SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM EBOOK");
    List<List<?>> res = bookCache.query(qry.setDistributedJoins(true)).getAll();

    ArrayList<eBook> books = new ArrayList<eBook>();



    @Override
    public List<List<?>> getListBook() {
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM EBOOK");
        List<List<?>> res = bookCache.query(qry.setDistributedJoins(true)).getAll();
        if(res != null) return res;
        throw new NotFoundException("Chưa có sách trong hệ thống");
    }

    @Override
    public eBook getBookById(String id) {
        eBookKey bookKey = new eBookKey(id, 1);
        eBook book = bookCache.get(bookKey);

        if (book != null) return book;

        throw new NotFoundException("Book không tồn tại trong hệ thống");
    }

    @Override
    public List<List<?>> searchBook(String keyword) {
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM EBOOK WHERE title LIKE \'%" + keyword + "%\';");
        List<List<?>> res = bookCache.query(qry.setDistributedJoins(true)).getAll();
        if (res != null) return res;
        throw new NotFoundException("Không tìm thấy sách");
    }
}
