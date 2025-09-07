package org.edu.miu.cs590de.book.client.service;

import lombok.Value;
import org.edu.miu.cs590de.book.client.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void add(BookDTO book);
    void update(BookDTO book, Long id);
    void delete(Long id);
    BookDTO get(Long id);
    List<BookDTO> getAll();

}
