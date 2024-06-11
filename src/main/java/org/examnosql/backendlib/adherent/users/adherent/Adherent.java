package org.examnosql.backendlib.adherent.users.adherent;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@NoArgsConstructor
@Document(collection = "adherent")

public class Adherent {
    @Id
    private String id;

    private String nom;
    private String prénom;
    private String username;
    private String motDePasse;

    private Contact contact;

    public String getId() {
        return id;
    }

    public Adherent(String nom, String prénom, String username, String motDePasse, Contact contact) {
        this.nom = nom;
        this.prénom = prénom;
        this.username = username;
        this.motDePasse = motDePasse;
        this.contact = contact;
    }

    public Adherent(String nom, String prénom, String username, Contact contact) {
        this.nom = nom;
        this.prénom = prénom;
        this.username = username;
        this.contact = contact;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public static class Contact {
        private String téléphone;
        private String mail;
        private String adresse;

        public Contact(String téléphone, String adresse) {
            this.téléphone = téléphone;
            this.adresse = adresse;
        }

        public String getTéléphone() {
            return téléphone;
        }

        public void setTéléphone(String téléphone) {
            this.téléphone = téléphone;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }
    }
}