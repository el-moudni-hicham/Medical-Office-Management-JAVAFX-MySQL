package presentation.controllers;

import com.jfoenix.controls.JFXComboBox;
import dao.ConsultationDaoImpl;
import dao.MedecinDaoImpl;
import dao.PatientDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ICabinetMetier;
import services.ICabinetMetierImpl;
import dao.entites.Consultation;
import dao.entites.Medecin;
import dao.entites.Patient;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML
    private JFXComboBox<Medecin> txtMedecin;

    @FXML
    private JFXComboBox<Patient> txtPatient;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TableColumn<Consultation, Integer> colId;

    @FXML
    private TableColumn<Consultation, Patient> colPatient;

    @FXML
    private TableColumn<Consultation, Medecin> colMedecin;

    @FXML
    private TableColumn<Consultation, Date> colDate;

    @FXML
    private TableView<Consultation> consultationTableView;

    ObservableList<Consultation> observableListCons = FXCollections.observableArrayList();
    ObservableList<Medecin> observableListMed = FXCollections.observableArrayList();
    ObservableList<Patient> observableListPat = FXCollections.observableArrayList();
    ICabinetMetier consultService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultService = new ICabinetMetierImpl(new PatientDaoImpl(),new ConsultationDaoImpl(),new MedecinDaoImpl());
        consultationTableView.setItems(observableListCons);
        observableListCons.setAll(consultService.getAllConsultations());

        colId.setCellValueFactory(new PropertyValueFactory<>("id_consultation"));
        colMedecin.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_consultation"));

        loadConsultations();

        observableListMed.setAll(consultService.getAllMedecins());
        txtMedecin.setItems(observableListMed);

        observableListPat.setAll(consultService.getAllPatients());
        txtPatient.setItems(observableListPat);
    }
    private void loadConsultations(){
        observableListCons.clear();
        observableListCons.addAll(consultService.getAllConsultations());
    }

    @FXML
    void addConsultation() {
        Patient patient = txtPatient.getValue();
        Medecin medecin = txtMedecin.getValue();
        LocalDate date = txtDate.getValue();
        int year = date.getYear();
        int month =  date.getMonthValue();
        int day = date.getDayOfMonth();

        Date d = new Date(year,month,day);
        Consultation consultation = new Consultation();
        consultation.setPatient(patient);
        consultation.setMedecin(medecin);
        consultation.setDate_consultation(d);

        consultService.addConsultation(consultation);
        loadConsultations();
    }

    @FXML
    void deleteConsultation() {
        MultipleSelectionModel<Consultation> consultation = consultationTableView.getSelectionModel();
        consultService.deleteConsultation(consultation.getSelectedItem());
        loadConsultations();
    }
}
