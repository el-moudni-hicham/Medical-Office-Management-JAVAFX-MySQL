package services;

import dao.ConsultationDao;
import dao.MedecinDao;
import dao.PatientDao;
import dao.entites.Consultation;
import dao.entites.Medecin;
import dao.entites.Patient;

import java.util.List;

public class ICabinetMetierImpl implements ICabinetMetier{

    PatientDao pdao;
    ConsultationDao cdao;
    MedecinDao mdao;

    public ICabinetMetierImpl() {
    }

    public ICabinetMetierImpl(PatientDao pdao, ConsultationDao cdao, MedecinDao mdao) {
        this.pdao = pdao;
        this.cdao = cdao;
        this.mdao = mdao;
    }

    //Patient
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

    //Medecin
    @Override
    public List<Medecin> getAllMedecins() {
        return mdao.findAll();
    }

    @Override
    public Medecin addMedecin(Medecin medecin) {
        return mdao.add(medecin);
    }

    @Override
    public boolean deleteMedecin(Medecin medecin) {
        return mdao.delete(medecin);
    }

    @Override
    public Medecin findMedecinById(int id) {
        return mdao.findOne(id);
    }

    @Override
    public List<Consultation> cocsultationsOfMedecin(Medecin medecin) {
        return mdao.consultationsOfMedecin(medecin);
    }

    //Consultation
    @Override
    public List<Consultation> getAllConsultations() {
        return cdao.findAll();
    }

    @Override
    public Consultation addConsultation(Consultation consultation) {
        return cdao.add(consultation);
    }

    @Override
    public boolean deleteConsultation(Consultation consultation) {
        return cdao.delete(consultation);
    }
}
