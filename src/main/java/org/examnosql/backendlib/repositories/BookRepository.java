package org.examnosql.backendlib.repositories;

import org.examnosql.backendlib.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByIsbn(String isbn);
    Book deleteBookByIsbn(String isbn);
    Optional<Book> findBookByTitle(String title);
}

