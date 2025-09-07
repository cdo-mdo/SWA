package org.edu.miu.cs590de.book.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookActivityDTO implements java.io.Serializable {
    private String action; // "ADD", "UPDATE", "DELETE"
    private String bookIsbn;
    private String title;
    private String author;

    @Override
    public String toString() {
        return "BookActivityDTO{" +
                "action='" + action + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
