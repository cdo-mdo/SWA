package org.edu.miu.cs590de.book.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class BookDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;
    private String author;
    private String isbn;
    private Float price;
}
