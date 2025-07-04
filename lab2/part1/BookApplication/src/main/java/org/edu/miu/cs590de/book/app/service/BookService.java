package org.edu.miu.cs590de.book.app.service;

import org.edu.miu.cs590de.book.app.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void add(Book book);
    void update(Book book);
    void delete(Long id);
    Optional<Book> get(Long id);
    List<Book> getAll();
}
