package com.example.small_api.service;


import com.example.small_api.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>() {{
        add(new Book(1, "Harry Potter and the Philosopher's Stone", 1));
        add(new Book(2, "A Game of Thrones", 2));
        add(new Book(3, "The Hobbit", 3));
        add(new Book(4, "Harry Potter and the Chamber of Secrets", 1));
        add(new Book(5, "A Clash of Kings", 2));
    }};
    private int currentId = 1;

    public Book createBook(String title, int authorId) {
        Book book = new Book(currentId++, title, authorId);
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    public Optional<Book> updateBook(int id, String title, int authorId) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(title);
                book.setAuthorId(authorId);
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public boolean deleteBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}
