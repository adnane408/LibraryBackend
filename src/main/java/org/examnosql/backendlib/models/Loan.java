package org.examnosql.backendlib.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "loans")
public class Loan {

    @Id
    private String id;
    private String livreId;
    private String adherentId;
    private String dateDePret;
    private String dateDeRetour;
    private String status;

    // Getters and Setters


    public Loan() {
    }

    public Loan(String id, String livreId, String adherentId, String dateDePret, String dateDeRetour, String status) {
        this.id = id;
        this.livreId = livreId;
        this.adherentId = adherentId;
        this.dateDePret = dateDePret;
        this.dateDeRetour = dateDeRetour;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLivreId() {
        return livreId;
    }

    public void setLivreId(String livreId) {
        this.livreId = livreId;
    }

    public String getAdherentId() {
        return adherentId;
    }

    public void setAdherentId(String adherentId) {
        this.adherentId = adherentId;
    }

    public String getDateDePret() {
        return dateDePret;
    }

    public void setDateDePret(String dateDePret) {
        this.dateDePret = dateDePret;
    }

    public String getDateDeRetour() {
        return dateDeRetour;
    }

    public void setDateDeRetour(String dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
