package services;

import dao.entites.Consultation;
import dao.entites.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient addPatient(Patient patient);
    boolean deletePatient(Patient patient);
    Patient updatePatient(Patient patient);
    Patient findPatientById(int id);
    List<Patient> findPatientByQuery(String mc);
    List<Consultation> cocsultationsOfPatient(Patient patient);
}
