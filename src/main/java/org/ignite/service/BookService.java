package org.ignite.service;


import org.ignite.Entity.eBook;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {
    public List<List<?>> getListBook();

    public eBook getBookById(String id);

    public List<List<?>> searchBook(String keyword);
}
