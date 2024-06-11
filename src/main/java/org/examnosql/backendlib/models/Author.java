package org.examnosql.backendlib.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    private String dateNaissance;

    @Relationship(type = "WROTE_BY", direction = Relationship.Direction.INCOMING)
    private Set<BookNode> bookNodes = new HashSet<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Set<BookNode> getBooks() {
        return bookNodes;
    }

    public void setBooks(Set<BookNode> bookNodes) {
        this.bookNodes = bookNodes;
    }
}
