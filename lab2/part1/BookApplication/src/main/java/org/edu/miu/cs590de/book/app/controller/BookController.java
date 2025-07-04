package org.edu.miu.cs590de.book.app.controller;

import org.edu.miu.cs590de.book.app.entity.Book;
import org.edu.miu.cs590de.book.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public Book get(@PathVariable Long id) {
        Optional<Book> book = bookService.get(id);
        return book.orElse(null);
    }

    @PostMapping
    public void add(@RequestBody Book book) {
        bookService.add(book);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Book book, @PathVariable Long id) {
        book.setId(id);
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
