package dao.entites;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {
    private int id_patient;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String email;
    private Date date_naissance;

    public Patient() {
    }

    public Patient(String nom, String prenom, String cin, String telephone, String email, Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.date_naissance = date_naissance;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
