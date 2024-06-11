package org.examnosql.backendlib.adherent.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepo extends MongoRepository<Utilisateur, String> {
    Optional<Utilisateur> findByUsername(String username);
}
