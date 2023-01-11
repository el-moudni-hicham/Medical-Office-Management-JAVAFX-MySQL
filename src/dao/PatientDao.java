package dao;

import dao.entites.Consultation;
import dao.entites.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient> {
    List<Patient> findByQuery(String mc);
    List<Consultation> consultationsOfPatient(Patient patient);
    Patient findPatientById(int id);
}
