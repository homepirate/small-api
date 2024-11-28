package com.example.small_api;

import com.example.small_api.controller.AuthorController;
import com.example.small_api.model.Author;
import com.example.small_api.service.AuthorService;
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

class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAuthor() {
        Author author = new Author(1, "J.K. Rowling");
        when(authorService.createAuthor(anyString())).thenReturn(author);

        Author createdAuthor = authorController.createAuthor(author);

        assertNotNull(createdAuthor);
        assertEquals("J.K. Rowling", createdAuthor.getName());
        verify(authorService, times(1)).createAuthor("J.K. Rowling");
    }

    @Test
    void getAllAuthors() {
        List<Author> authors = Arrays.asList(new Author(1, "J.K. Rowling"), new Author(2, "George R.R. Martin"));
        when(authorService.getAllAuthors()).thenReturn(authors);

        List<Author> result = authorController.getAllAuthors();

        assertEquals(2, result.size());
        assertEquals("J.K. Rowling", result.get(0).getName());
    }

    @Test
    void getAuthorById() {
        Author author = new Author(1, "J.K. Rowling");
        when(authorService.getAuthorById(1)).thenReturn(Optional.of(author));

        Author result = authorController.getAuthorById(1);

        assertNotNull(result);
        assertEquals("J.K. Rowling", result.getName());
    }

    @Test
    void updateAuthor() {
        Author author = new Author(1, "Updated Name");
        when(authorService.updateAuthor(eq(1), anyString())).thenReturn(Optional.of(author));

        Author result = authorController.updateAuthor(1, author);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(authorService, times(1)).updateAuthor(1, "Updated Name");
    }

    @Test
    void deleteAuthor() {
        when(authorService.deleteAuthor(1)).thenReturn(true);

        String response = authorController.deleteAuthor(1);

        assertEquals("Author deleted", response);
        verify(authorService, times(1)).deleteAuthor(1);
    }
}

