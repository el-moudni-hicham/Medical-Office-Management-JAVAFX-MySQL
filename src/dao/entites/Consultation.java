package dao.entites;

import java.io.Serializable;
import java.util.Date;

public class Consultation implements Serializable {
    private int id_consultation;
    private Date date_consultation;
    private Patient patient;
    private Medecin medecin;

    public Consultation() {
    }

    public Consultation(int id_consultation, Date date_consultation, Patient patient, Medecin medecin) {
        this.id_consultation = id_consultation;
        this.date_consultation = date_consultation;
        this.patient = patient;
        this.medecin = medecin;
    }

    public int getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(int id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Date getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(Date date_consultation) {
        this.date_consultation = date_consultation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return
                        "Numero de consultation : " + id_consultation +"\n"+
                        "Patient : " + patient.getPrenom() + " " + patient.getNom() +"\n"+
                        "Medecin : " + medecin.getPrenom() + " " + medecin.getNom() +"\n"+
                        "Date de consultation : " + date_consultation+"\n"+
                        "________________________________________________________";
    }
}
