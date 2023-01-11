package services;

import dao.MedecinDao;
import dao.entites.Consultation;
import dao.entites.Medecin;

import java.util.List;

public class MedecinServiceImpl implements MedecinService{
    MedecinDao mdao ;

    public MedecinServiceImpl(MedecinDao mdao) {
        this.mdao = mdao;
    }

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
}
