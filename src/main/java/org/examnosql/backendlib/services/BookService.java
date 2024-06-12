package org.examnosql.backendlib.services;

import org.examnosql.backendlib.models.Author;
import org.examnosql.backendlib.models.Book;
import org.examnosql.backendlib.models.BookNode;
import org.examnosql.backendlib.repositories.AuthorRepository;
import org.examnosql.backendlib.repositories.BookNodeRepository;
import org.examnosql.backendlib.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookNodeRepository bookNodeRepository;

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    public Book saveBook(Book book) {
        if (bookNodeRepository.findByIsbn(book.getIsbn()) != null) return null;

        List<Author> existingAuthors = book.getAuthors().stream()
                .map(author -> {
                    Author existingAuthor = authorRepository.findByNomAndPrenomAndDateNaissance(author.getNom(), author.getPrenom(), author.getDateNaissance());
                    return existingAuthor != null ? existingAuthor : author;
                })
                .collect(Collectors.toList());

        book.setAuthors(existingAuthors);

        Book savedBook = bookRepository.save(book);
        BookNode bookNode = new BookNode();
        bookNode.setIsbn(savedBook.getIsbn());
        bookNode.setTitle(savedBook.getTitle());
        bookNode.setAuthors(savedBook.getAuthors());
        bookNodeRepository.save(bookNode);
        createCoAuthorRelationships(book.getAuthors());

        return savedBook;
    }


    public void deleteBook(String isbn) {
        bookRepository.deleteBookByIsbn(isbn);
        bookNodeRepository.deleteBookNodeByIsbn(isbn);
    }


    public Book updateBook(Book book) {
        Book book1= bookRepository.findByIsbn(book.getIsbn());
        if(book1!=null) {
            book1.setNmbCopie(book1.getNmbCopie()+book.getNmbCopie());
            book1.setAuthors(book.getAuthors());
            return bookRepository.save(book1);
        };
        return saveBook(book);


    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public BookNode findBookByTitle(String title) {
        return bookNodeRepository.findByTitle(title);
    }

    private void createCoAuthorRelationships(List<Author> authors) {
        for (int i = 0; i < authors.size(); i++) {
            for (int j = i + 1; j < authors.size(); j++) {
                Author author1 = authors.get(i);
                Author author2 = authors.get(j);

                // Add each author to the other's coAuthors set
                author1.getCoAuthors().add(author2);
                author2.getCoAuthors().add(author1);

                // Save the updated authors
                authorRepository.save(author1);
                authorRepository.save(author2);
            }
        }
    }
}
