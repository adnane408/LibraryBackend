package org.examnosql.backendlib.models;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
public class BookNode {
    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String title;
    private String datePublication;

    @Relationship(type = "WROTE", direction = Relationship.Direction.INCOMING)

    private List<Author> authors;
    // getters and setters


    public BookNode() {
    }

    public BookNode(Long id, String isbn, String title, List<Author> authors) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
