package org.examnosql.backendlib.repositories;

import org.examnosql.backendlib.models.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Long> {
    Author findByNomAndPrenomAndDateNaissance(String nom,String prenom,String ddn);
}