package org.examnosql.backendlib.adherent.users.adherent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.PanelUI;

@Repository
public interface AdherentRepository extends MongoRepository<Adherent, String> {
    public void deleteAdherentByUsername(String username);
}