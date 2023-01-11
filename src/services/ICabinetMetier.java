package services;

import dao.entites.Consultation;
import dao.entites.Medecin;
import dao.entites.Patient;

import java.util.List;

public interface ICabinetMetier {
    //Patient
    List<Patient> getAllPatients();
    Patient addPatient(Patient patient);
    boolean deletePatient(Patient patient);
    Patient updatePatient(Patient patient);
    Patient findPatientById(int id);
    List<Patient> findPatientByQuery(String mc);
    List<Consultation> cocsultationsOfPatient(Patient patient);

    //Medecin
    List<Medecin> getAllMedecins();
    Medecin addMedecin(Medecin medecin);
    boolean deleteMedecin(Medecin medecin);
    Medecin findMedecinById(int id);
    List<Consultation> cocsultationsOfMedecin(Medecin medecin);

    //Consultation
    List<Consultation> getAllConsultations();
    Consultation addConsultation(Consultation consultation);
    boolean deleteConsultation(Consultation consultation);
}
