package dao;

import dao.entites.Consultation;
import dao.entites.Medecin;
import dao.entites.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao{
    MedecinDao mdao = new MedecinDaoImpl();
    @Override
    public List<Patient> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Patient patient = new Patient();
                patient.setId_patient(rs.getInt(1));
                patient.setNom(rs.getString(2));
                patient.setPrenom(rs.getString(3));
                patient.setCin(rs.getString(4));
                patient.setTelephone(rs.getString(5));
                patient.setEmail(rs.getString(6));
                patient.setDate_naissance(rs.getDate(7));
                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient add(Patient patient) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO PATIENTS(NOM,PRENOM,CIN,TELEPHONE,EMAIL,DATE_NAISSANCE) VALUES(?,?,?,?,?,?)");
            pstm.setString(1, patient.getNom());
            pstm.setString(2,patient.getPrenom());
            pstm.setString(3, patient.getCin());
            pstm.setString(4,patient.getTelephone());
            pstm.setString(5,patient.getEmail());
            java.util.Date date = patient.getDate_naissance();
            pstm.setDate(6,new Date(date.getYear()-1900,date.getMonth()-1,date.getDate()));
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public boolean delete(Patient patient) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM PATIENTS WHERE ID_PATIENT=?");
            pstm.setInt(1,patient.getId_patient());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Patient update(Patient patient) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE PATIENTS SET NOM=?,PRENOM=?,CIN=?,TELEPHONE=?,EMAIL=?,DATE_NAISSANCE=? WHERE ID_PATIENT=?");
            pstm.setString(1,patient.getNom());
            pstm.setString(2,patient.getPrenom());
            pstm.setString(3,patient.getCin());
            pstm.setString(4,patient.getTelephone());
            pstm.setString(5,patient.getEmail());
            java.util.Date date = patient.getDate_naissance();
            pstm.setDate(6,new Date(date.getYear(),date.getMonth(),date.getDate()));
            pstm.setInt(7,patient.getId_patient());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public Patient findOne(int id) {
        return null;
    }

    @Override
    public Patient findPatientById(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        Patient patient = new Patient();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS WHERE ID_PATIENT=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                patient.setId_patient(rs.getInt(1));
                patient.setNom(rs.getString(2));
                patient.setPrenom(rs.getString(3));
                patient.setCin(rs.getString(4));
                patient.setTelephone(rs.getString(5));
                patient.setEmail(rs.getString(6));
                patient.setDate_naissance(rs.getDate(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public List<Patient> findByQuery(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS WHERE NOM LIKE ? OR PRENOM LIKE ? OR CIN LIKE ? OR TELEPHONE LIKE ? OR EMAIL LIKE ? OR DATE_NAISSANCE LIKE ?");//OR PRENOM LIKE ? OR CIN LIKE ? OR TELEPHONE LIKE ? OR EMAIL LIKE ? OR DATE_NAISSANCE LIKE ?
            pstm.setString(1,"%"+mc+"%");
            pstm.setString(2,"%"+mc+"%");
            pstm.setString(3,"%"+mc+"%");
            pstm.setString(4,"%"+mc+"%");
            pstm.setString(5,"%"+mc+"%");
            pstm.setString(6,"%"+mc+"%");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Patient patient = new Patient();
                patient.setId_patient(rs.getInt(1));
                patient.setNom(rs.getString(2));
                patient.setPrenom(rs.getString(3));
                patient.setCin(rs.getString(4));
                patient.setTelephone(rs.getString(5));
                patient.setEmail(rs.getString(6));
                patient.setDate_naissance(rs.getDate(7));
                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public List<Consultation> consultationsOfPatient(Patient patient) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CONSULTATIONS WHERE ID_PATIENT = ?");
            pstm.setInt(1,patient.getId_patient());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Consultation consultation = new Consultation();
                consultation.setId_consultation(rs.getInt(1));
                consultation.setDate_consultation(rs.getDate(2));
                consultation.setPatient(findPatientById(rs.getInt(3)));
                consultation.setMedecin(mdao.findMedecinById(rs.getInt(4)));
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return consultations;
    }
}
