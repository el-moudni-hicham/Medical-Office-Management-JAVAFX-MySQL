package dao;

import dao.entites.Consultation;
import dao.entites.Medecin;
import dao.entites.Patient;

import java.util.Date;
import java.util.List;

public class DaoApplication {
    public static void main(String[] args) {
        PatientDao pdao = new PatientDaoImpl();
        MedecinDao mdao = new MedecinDaoImpl();
        ConsultationDao cdao = new ConsultationDaoImpl();
        /*Patient p = new Patient();
        p.setNom("tyew");
        p.setPrenom("said");
        p.setEmail("sad@gmail.com");
        p.setCin("tr3425");
        p.setTelephone("+2125363782");
        Date date = new Date(1999,03,17);
        p.setDate_naissance(date);
        pdao.add(p);

        List<Patient> patientList = pdao.findAll();
        for (Patient p:patientList) {
            System.out.println(p.toString());
        }

        System.out.println(pdao.findOne(1).toString());

        List<Patient> patientList = pdao.findByQuery("pat");
        for (Patient p:patientList) {
            System.out.println(p.toString());
        }

List<Consultation> consultations = pdao.consultationsOfPatient(pdao.findOne(1));
        for (Consultation consultation:consultations) {
            System.out.println(consultation.toString());
        }
         */



        /*
        pdao.consultationsOfPatient();
        pdao.delete();
        pdao.update();
         */

        Patient p = pdao.findPatientById(1);

        Medecin m = mdao.findMedecinById(1);

        Date date1 = new Date(1999,03,17);
        Consultation consultation = new Consultation(1,date1,p,m);
        cdao.add(consultation);

    }
}
