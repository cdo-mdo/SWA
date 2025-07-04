package org.edu.miu.cs590de.book.app.service;

import org.edu.miu.cs590de.book.app.dto.BookDTO;
import org.edu.miu.cs590de.book.app.entity.Book;
import org.edu.miu.cs590de.book.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;
    @Override
    public void add(BookDTO bookDTO) {
        repository.save(Book.fromDTO(bookDTO));
    }
    @Override
    public void update(BookDTO bookDTO) {
        repository.save(Book.fromDTO(bookDTO));
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<BookDTO> get(Long id) {
        Optional<Book> book = repository.findById(id);
        return book.map(value -> Optional.of(new BookDTO(value))).orElse(null);
    }
    @Override
    public List<BookDTO> getAll() {
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : repository.findAll()) {
            bookDTOs.add(new BookDTO(book));
        }
        return bookDTOs;
    }

}
