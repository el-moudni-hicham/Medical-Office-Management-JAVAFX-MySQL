package dao;

import dao.entites.Consultation;
import dao.entites.Medecin;

import java.util.List;

public interface MedecinDao extends Dao<Medecin> {
    List<Consultation> consultationsOfMedecin(Medecin medecin);
    Medecin findMedecinById(int id);
}
