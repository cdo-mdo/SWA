package org.edu.miu.cs590de.book.client.controller;

import org.edu.miu.cs590de.book.client.dto.BookDTO;
import org.edu.miu.cs590de.book.client.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public BookDTO get(@PathVariable Long id) {
        return bookService.get(id);
    }

    @PostMapping
    public void add(@RequestBody BookDTO bookDTO) {
        bookService.add(bookDTO);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody BookDTO book, @PathVariable Long id) {
        book.setId(id);
        bookService.update(book, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
