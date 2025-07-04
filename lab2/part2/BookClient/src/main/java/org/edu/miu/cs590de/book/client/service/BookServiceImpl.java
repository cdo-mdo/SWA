package org.edu.miu.cs590de.book.client.service;

import java.util.List;

import org.edu.miu.cs590de.book.client.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class BookServiceImpl implements BookService {
    @Value("${external.api.books.url}")
    private String bookApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<BookDTO> getAll() {
        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
                bookApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>() {}
        );
        return response.getBody();
    }

    public BookDTO get(Long id) {
        ResponseEntity<BookDTO> response = restTemplate.exchange(
            bookApiUrl + "/" +  id,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<BookDTO>() {}
        );
        return response.getBody();
    }

    public void add(BookDTO bookDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<BookDTO> request = new HttpEntity<>(bookDTO, headers);
        ResponseEntity<?> response = restTemplate.exchange(
                bookApiUrl,
                HttpMethod.POST,
                request,
                Void.class
        );
    }

    public void update(BookDTO bookDTO, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<BookDTO> request = new HttpEntity<>(bookDTO, headers);
        ResponseEntity<?> response = restTemplate.exchange(
                bookApiUrl + "/" + id,
                HttpMethod.PUT,
                request,
                Void.class
        );
    }

    @Override
    public void delete(Long bookId) {
        String url = bookApiUrl + "/" + bookId;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );
    }
}
