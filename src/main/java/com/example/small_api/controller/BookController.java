package com.example.small_api.controller;

import com.example.small_api.model.Book;
import com.example.small_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book.getTitle(), book.getAuthorId());
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.orElse(null); // Вернем null если не найден
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        Optional<Book> updatedBook = bookService.updateBook(id, book.getTitle(), book.getAuthorId());
        return updatedBook.orElse(null); // Вернем null если не найден
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id) ? "Book deleted" : "Book not found";
    }
}
