package com.example.small_api.service;


import com.example.small_api.model.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private List<Author> authors = new ArrayList<>() {{
        add(new Author(1, "J.K. Rowling"));
        add(new Author(2, "George R.R. Martin"));
        add(new Author(3, "J.R.R. Tolkien"));
    }};

    private int currentId = 1;

    public Author createAuthor(String name) {
        Author author = new Author(currentId++, name);
        authors.add(author);
        return author;
    }

    public List<Author> getAllAuthors() {
        return authors;
    }

    public Optional<Author> getAuthorById(int id) {
        return authors.stream().filter(author -> author.getId() == id).findFirst();
    }

    public Optional<Author> updateAuthor(int id, String name) {
        for (Author author : authors) {
            if (author.getId() == id) {
                author.setName(name);
                return Optional.of(author);
            }
        }
        return Optional.empty();
    }

    public boolean deleteAuthor(int id) {
        return authors.removeIf(author -> author.getId() == id);
    }
}
