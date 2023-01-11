package dao;

import dao.entites.Consultation;
import dao.entites.Medecin;
import dao.entites.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinDaoImpl implements MedecinDao{
    @Override
    public List<Medecin> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Medecin> medecins = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM MEDECINS");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Medecin medecin = new Medecin();
                medecin.setId_medecin(rs.getInt(1));
                medecin.setNom(rs.getString(2));
                medecin.setPrenom(rs.getString(3));
                medecin.setTelephone(rs.getString(4));
                medecin.setEmail(rs.getString(5));
                medecins.add(medecin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medecins;
    }

    @Override
    public Medecin add(Medecin medecin) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO MEDECINS(NOM,PRENOM,TELEPHONE,EMAIL) VALUES(?,?,?,?)");
            pstm.setString(1, medecin.getNom());
            pstm.setString(2,medecin.getPrenom());
            pstm.setString(3,medecin.getTelephone());
            pstm.setString(4,medecin.getEmail());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medecin;
    }

    @Override
    public boolean delete(Medecin medecin) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM MEDECINS WHERE ID_MEDECIN=?");
            pstm.setInt(1,medecin.getId_medecin());
            pstm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public Medecin update(Medecin medecin) {
        return null;
    }

    @Override
    public Medecin findOne(int id) {
        return null;
    }


    @Override
    public Medecin findMedecinById(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        Medecin medecin = new Medecin();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM MEDECINS WHERE ID_MEDECIN=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                medecin.setId_medecin(rs.getInt(1));
                medecin.setNom(rs.getString(2));
                medecin.setPrenom(rs.getString(3));
                medecin.setTelephone(rs.getString(4));
                medecin.setEmail(rs.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medecin;
    }


    @Override
    public List<Consultation> consultationsOfMedecin(Medecin medecin) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CONSULTATIONS WHERE ID_MEDECIN = ?");
            pstm.setInt(1,medecin.getId_medecin());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Consultation consultation = new Consultation();
                consultation.setId_consultation(rs.getInt(1));
                consultation.setDate_consultation(rs.getDate(2));
                consultation.setPatient((Patient) rs.getObject(3));
                consultation.setMedecin((Medecin) rs.getObject(4));
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return consultations;
    }
}
