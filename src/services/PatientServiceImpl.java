package services;

import dao.PatientDao;
import dao.PatientDaoImpl;
import dao.entites.Consultation;
import dao.entites.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService{
    PatientDao pdao;

    public PatientServiceImpl(PatientDao pdao) {
        this.pdao = pdao;
    }

    @Override
    public List<Patient> getAllPatients() {
       return pdao.findAll();
    }

    @Override
    public Patient addPatient(Patient patient) {
        return pdao.add(patient);
    }

    @Override
    public boolean deletePatient(Patient patient) {
        return pdao.delete(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return pdao.update(patient);
    }

    @Override
    public Patient findPatientById(int id) {
        return pdao.findOne(id);
    }

    @Override
    public List<Patient> findPatientByQuery(String mc) {
        return pdao.findByQuery(mc);
    }

    @Override
    public List<Consultation> cocsultationsOfPatient(Patient patient) {
        return pdao.consultationsOfPatient(patient);
    }
}
