package org.examnosql.backendlib.repositories;

import org.examnosql.backendlib.models.BookNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BookNodeRepository extends Neo4jRepository<BookNode, Long> {
    BookNode findByTitle(String title);
    BookNode findByIsbn(String isbn);
    void deleteBookNodeByIsbn(String isbn);
}
