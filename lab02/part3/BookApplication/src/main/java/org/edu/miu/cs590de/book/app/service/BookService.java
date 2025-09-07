package org.edu.miu.cs590de.book.app.service;

import org.edu.miu.cs590de.book.app.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void add(BookDTO book);
    void update(BookDTO book);
    void delete(Long id);
    Optional<BookDTO> get(Long id);
    List<BookDTO> getAll();
}
