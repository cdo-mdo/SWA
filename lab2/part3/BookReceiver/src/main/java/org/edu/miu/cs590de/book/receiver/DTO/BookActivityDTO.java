package org.edu.miu.cs590de.book.receiver.DTO;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BookActivityDTO implements Serializable {
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
