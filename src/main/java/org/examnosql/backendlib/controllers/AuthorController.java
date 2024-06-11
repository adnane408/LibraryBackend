package org.examnosql.backendlib.controllers;

import org.examnosql.backendlib.services.AuthorService;
import org.examnosql.backendlib.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        return author.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Optional<Author> optionalAuthor = authorService.findById(id);

        if (!optionalAuthor.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Author author = optionalAuthor.get();
        author.setNom(authorDetails.getNom());
        author.setPrenom(authorDetails.getPrenom());
        author.setDateNaissance(authorDetails.getDateNaissance());

        Author updatedAuthor = authorService.save(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (!authorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
