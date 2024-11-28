package com.example.small_api;

import com.example.small_api.controller.BookController;
import com.example.small_api.model.Book;
import com.example.small_api.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBook() {
        Book book = new Book(1, "Harry Potter", 1);
        when(bookService.createBook(anyString(), anyInt())).thenReturn(book);

        Book createdBook = bookController.createBook(book);

        assertNotNull(createdBook);
        assertEquals("Harry Potter", createdBook.getTitle());
        verify(bookService, times(1)).createBook("Harry Potter", 1);
    }

    @Test
    void getAllBooks() {
        List<Book> books = Arrays.asList(new Book(1, "Harry Potter", 1), new Book(2, "A Game of Thrones", 2));
        when(bookService.getAllBooks()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());
    }

    @Test
    void getBookById() {
        Book book = new Book(1, "Harry Potter", 1);
        when(bookService.getBookById(1)).thenReturn(Optional.of(book));

        Book result = bookController.getBookById(1);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());
    }

    @Test
    void updateBook() {
        Book book = new Book(1, "Updated Title", 1);
        when(bookService.updateBook(eq(1), anyString(), anyInt())).thenReturn(Optional.of(book));

        Book result = bookController.updateBook(1, book);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(bookService, times(1)).updateBook(1, "Updated Title", 1);
    }

    @Test
    void deleteBook() {
        when(bookService.deleteBook(1)).thenReturn(true);

        String response = bookController.deleteBook(1);

        assertEquals("Book deleted", response);
        verify(bookService, times(1)).deleteBook(1);
    }
}
