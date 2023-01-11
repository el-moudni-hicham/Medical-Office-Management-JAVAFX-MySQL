package services;

import dao.entites.Consultation;
import dao.entites.Medecin;


import java.util.List;

public interface MedecinService {
    List<Medecin> getAllMedecins();
    Medecin addMedecin(Medecin medecin);
    boolean deleteMedecin(Medecin medecin);
    Medecin findMedecinById(int id);
    List<Consultation> cocsultationsOfMedecin(Medecin medecin);
}
