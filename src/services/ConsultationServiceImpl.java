package services;

import dao.ConsultationDao;
import dao.entites.Consultation;

import java.util.List;

public class ConsultationServiceImpl implements ConsultationService{
    ConsultationDao cdao;

    public ConsultationServiceImpl(ConsultationDao cdao) {
        this.cdao = cdao;
    }

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
