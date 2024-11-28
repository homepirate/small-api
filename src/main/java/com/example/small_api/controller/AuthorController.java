package com.example.small_api.controller;

import com.example.small_api.model.Author;
import com.example.small_api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author.getName());
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> author = authorService.getAuthorById(id);
        return author.orElse(null); // Вернем null если не найден
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Optional<Author> updatedAuthor = authorService.updateAuthor(id, author.getName());
        return updatedAuthor.orElse(null); // Вернем null если не найден
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable int id) {
        return authorService.deleteAuthor(id) ? "Author deleted" : "Author not found";
    }
}
