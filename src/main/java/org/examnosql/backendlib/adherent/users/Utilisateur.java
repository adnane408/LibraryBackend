package org.examnosql.backendlib.adherent.users;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utilisateurs")
@Data
public class Utilisateur {

    @Id
    private String id;
    private String username;
    private String motDePasse;
    private String role;

    public Utilisateur(String username, String motDePasse, String role) {
        this.username = username;
        this.motDePasse = motDePasse;
        this.role = role;
    }
}
