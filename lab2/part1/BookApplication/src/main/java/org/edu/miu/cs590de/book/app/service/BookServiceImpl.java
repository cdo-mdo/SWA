package org.edu.miu.cs590de.book.app.service;

import org.edu.miu.cs590de.book.app.entity.Book;
import org.edu.miu.cs590de.book.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;
    @Override
    public void add(Book book) {
        repository.save(book);
    }
    @Override
    public void update(Book book) {
        repository.save(book);
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<Book> get(Long id) {
        return repository.findById(id);
    }
    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

}
