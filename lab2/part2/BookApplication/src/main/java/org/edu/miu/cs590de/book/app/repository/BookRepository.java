package org.edu.miu.cs590de.book.app.repository;

import org.edu.miu.cs590de.book.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
