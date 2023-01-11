package dao;

import dao.entites.Consultation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements ConsultationDao{
    MedecinDao mdao = new MedecinDaoImpl();
    PatientDao pdao = new PatientDaoImpl();
    @Override
    public List<Consultation> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CONSULTATIONS");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Consultation consultation = new Consultation();
                consultation.setId_consultation(rs.getInt(1));
                consultation.setDate_consultation(rs.getDate(2));
                consultation.setPatient(pdao.findPatientById(rs.getInt(3)));
                consultation.setMedecin(mdao.findMedecinById(rs.getInt(4)));
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }

    @Override
    public Consultation add(Consultation consultation) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO CONSULTATIONS(DATE_CONSULTATION,ID_PATIENT,ID_MEDECIN) VALUES(?,?,?)");
            java.util.Date date = consultation.getDate_consultation();
            pstm.setDate(1,new Date(date.getYear()-1900,date.getMonth()-1,date.getDate()));
            if(!consultation.getPatient().equals(null))
                pstm.setInt(2,consultation.getPatient().getId_patient());
            if(!consultation.getMedecin().equals(null))
                pstm.setInt(3,consultation.getMedecin().getId_medecin());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultation;
    }

    @Override
    public boolean delete(Consultation consultation) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM CONSULTATIONS WHERE ID_CONSULTATION=?");
            pstm.setInt(1,consultation.getId_consultation());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public Consultation update(Consultation consultation) {
        return null;
    }

    @Override
    public Consultation findOne(int id) {
        return null;
    }
}
