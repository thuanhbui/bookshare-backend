package org.ignite.service;


import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Dao.BookRepository;
import org.ignite.Dao.LikeRepository;
import org.ignite.Entity.*;
import org.ignite.config.IgniteConfig;
import org.ignite.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static java.sql.Types.NULL;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<eBookDto> getLikedBook(Integer userId) {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByLike(userId);
        List<eBookDto> bookDtos = new ArrayList<>();
        for (Cache.Entry<eBookKey, eBook> entry : entries) {
            eBookDto bookDto = new eBookDto(entry.getKey(), entry.getValue());
            bookDto.setCheckLike(1);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    public LikeDto toggleLike(Integer userId, String bookId) {
        IgniteCache<LikeKey, Like> cache = likeRepository.cache();
        LikeKey key = new LikeKey(userId, bookId);
        Like value = cache.get(key);
        if (value == null) {
            increaseLikes(bookId);
            Like value1 = new Like(1);
            cache.put(key, value1);
            return new LikeDto(key, value1);
        } else if (value.getLiked() == 0) {
            increaseLikes(bookId);
            value.setLiked(1);
            cache.replace(key, value);
            return new LikeDto(key, value);
        } else {
            decreaseLikes(bookId);
            value.setLiked(0);
            cache.replace(key, value);
            return new LikeDto(key, value);
        }

    }

    public eBookDto checkLike(String bookId, Integer userId) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(bookId);
        if (entry == null)  return  null;
        eBookDto bookDto = new eBookDto(entry.getKey(), entry.getValue());
        if (userId == null) {
            bookDto.setCheckLike(0);
        } else {
            IgniteCache<LikeKey, Like> cache = likeRepository.cache();
            LikeKey key = new LikeKey(userId, bookId);
            bookDto.setCheckLike(cache.get(key).getLiked());
        }

        return bookDto;
    }

    public List<eBookDto> findBookByTitle(String title) {
        title = '%' + WordUtils.capitalizeFully(title) + '%';
        System.out.println(title);
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByKeyWord(title);
        List<eBookDto> bookDtos = new ArrayList<>();
        for(Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }

    public List<eBookDto> findBookByKeyUserCata(String keyWord, Integer userId, Integer cataId) {
        keyWord = '%' + WordUtils.capitalizeFully(keyWord) + '%';
        if (userId == null && cataId == null) {
            List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByKeyWord(keyWord);
            List<eBookDto> bookDtos = new ArrayList<>();
            for(Cache.Entry<eBookKey, eBook> entry : entries) {
                bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
            }
            return bookDtos;
        } else {
            if (userId == null) {
                List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByKeyCata(keyWord, cataId);
                List<eBookDto> bookDtos = new ArrayList<>();
                for(Cache.Entry<eBookKey, eBook> entry : entries) {
                    bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
                }
                return bookDtos;
            } else if (cataId == null){
                List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByKeyUser(keyWord, userId);
                List<eBookDto> bookDtos = new ArrayList<>();
                for(Cache.Entry<eBookKey, eBook> entry : entries) {
                    bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
                }
                return bookDtos;
            } else {
                List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByKeyUserCata(keyWord, userId, cataId);
                List<eBookDto> bookDtos = new ArrayList<>();
                for(Cache.Entry<eBookKey, eBook> entry : entries) {
                    bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
                }
                return bookDtos;
            }
        }
    }

    public eBookDto findBookById(String book_id) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(book_id);
        if (entry == null)  return  null;
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

    public List<eBookDto> getListNewBooks() {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.getListNewBooks();
        List<eBookDto> bookDtos = new ArrayList<>();
        for (Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }

    public List<eBookDto> getTop10(Integer catalogId) {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.getTop10(catalogId);
        List<eBookDto> bookDtos = new ArrayList<>();
        for (Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }


    public List<eBookDto> getBooksByCatalogId(Integer catalogId) {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByCatalogId(catalogId);
        List<eBookDto> bookDtos = new ArrayList<>();
        for (Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }

    public List<eBookDto> getBooksByUserId(Integer catalogId) {
        List<Cache.Entry<eBookKey, eBook>> entries = bookRepository.findByUserId(catalogId);
        List<eBookDto> bookDtos = new ArrayList<>();
        for (Cache.Entry<eBookKey, eBook> entry : entries) {
            bookDtos.add(new eBookDto(entry.getKey(), entry.getValue()));
        }
        return bookDtos;
    }

    public eBookDto updateBook(String bookId, eBook book) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(bookId);
        IgniteCache cache = bookRepository.cache();
        eBook book1 = (eBook) cache.get(entry.getKey());
        if (book.getTitle() != null)   book1.setTitle(WordUtils.capitalizeFully(book.getTitle()));
        if (book.getDescription() != null) book1.setDescription(book.getDescription());
        if (book.getImageLink() != null) book1.setImageLink(book.getImageLink());
        if (book.getFileLink() != null) book1.setFileLink(book.getFileLink());
        if (book.getLanguage() != null) book1.setLanguage(book.getLanguage());
        if (book.getReleaseYear() != null) book1.setReleaseYear(book.getReleaseYear());
        book1.setLastUpdate(new java.sql.Date(System.currentTimeMillis()));
        if (book.getCatalogId() != NULL) book1.setCatalogId(book.getCatalogId());
        cache.replace(entry.getKey(), book1);
        return new eBookDto(entry.getKey(), book1);
    }

    public eBookDto increaseLikes(String bookId) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(bookId);
        IgniteCache cache = bookRepository.cache();
        eBook book = (eBook) cache.get(entry.getKey());
        book.setLikes(book.getLikes() + 1);
        cache.replace(entry.getKey(), book);
        return new eBookDto(entry.getKey(), book);
    }

    public eBookDto decreaseLikes(String bookId) {
        Cache.Entry<eBookKey, eBook> entry = bookRepository.findById(bookId);
        IgniteCache cache = bookRepository.cache();
        eBook book = (eBook) cache.get(entry.getKey());
        if (book.getLikes() != 0) book.setLikes(book.getLikes() - 1);
        cache.replace(entry.getKey(), book);
        return new eBookDto(entry.getKey(), book);
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteByBookId(bookId);
    }

    public eBookDto addBook(eBook value, int userID) {
        eBookKey key = new eBookKey(UUID.randomUUID().toString(), 1);
        value.setLastUpdate(new java.sql.Date(System.currentTimeMillis()));
        value.setLikes(0);
        value.setTitle(WordUtils.capitalizeFully(value.getTitle()));
        bookRepository.cache().put(key, value);
        return new eBookDto(key, value);
    }

}
