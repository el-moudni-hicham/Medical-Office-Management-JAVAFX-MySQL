package services;

import dao.entites.Consultation;
import dao.entites.Patient;

import java.util.List;

public interface ConsultationService {
    List<Consultation> getAllConsultations();
    Consultation addConsultation(Consultation consultation);
    boolean deleteConsultation(Consultation consultation);
}
