package presentation.controllers;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.DatePickerContent;
import dao.ConsultationDaoImpl;
import dao.MedecinDaoImpl;
import dao.PatientDaoImpl;
import dao.entites.Consultation;
import dao.entites.Patient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import services.ICabinetMetier;
import services.ICabinetMetierImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtPrenom;

    @FXML
    private JFXTextField txtCin;

    @FXML
    private JFXTextField txtTele;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTextField txtRecherche;

    @FXML
    private TableColumn<Patient, Integer> colId;

    @FXML
    private TableColumn<Patient, String> colNom;

    @FXML
    private TableColumn<Patient, String> colPrenom;

    @FXML
    private TableColumn<Patient, String> colCin;

    @FXML
    private TableColumn<Patient, String> colTelephone;

    @FXML
    private TableColumn<Patient, String> colEmail;

    @FXML
    private TableColumn<Patient, Date> colDateNaissance;

    @FXML
    private Label txtConslt;

    @FXML
    private TableView<Patient> patientsTableView;

    @FXML
    private ListView<Consultation> consultationsListView;

    private ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Consultation> consultationObservableList = FXCollections.observableArrayList();

    private ICabinetMetier patientService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientService = new ICabinetMetierImpl(new PatientDaoImpl(),new ConsultationDaoImpl(),new MedecinDaoImpl());
        patientsTableView.setItems(patientObservableList);

        colId.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));

        txtRecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                patientObservableList.clear();
                patientObservableList.addAll(patientService.findPatientByQuery(newValue));
            }
        });

        patientsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            editPatientCols();
            });

        loadPatients();
    }

    private void loadPatients(){
        patientObservableList.clear();
        patientObservableList.addAll(patientService.getAllPatients());
    }
    @FXML
    void consultationOfPatient(ActionEvent event) {
        MultipleSelectionModel<Patient> patient = patientsTableView.getSelectionModel();
        if(!patient.isEmpty()) {
            Patient pat = patient.getSelectedItem();
            ICabinetMetier consultService = new ICabinetMetierImpl(new PatientDaoImpl(), new ConsultationDaoImpl(), new MedecinDaoImpl());
            consultationsListView.setItems(consultationObservableList);
            consultationObservableList.clear();
            consultationObservableList.addAll(consultService.cocsultationsOfPatient(pat));
            consultationsListView.setVisible(true);
            txtConslt.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez selectionner un element ! !");
            alert.show();
        }

    }

    @FXML
    void deletePatient(ActionEvent event) {
        MultipleSelectionModel<Patient> patient = patientsTableView.getSelectionModel();
        if(!patient.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Veuillez vous supprimer ce patient ?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                patientService.deletePatient(patient.getSelectedItem());
                //produitList.remove(produit.getSelectedIndex());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setContentText("Patient a ete supprimer avec succes");
                alert1.show();
                loadPatients();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez selectionner un element ! !");
            alert.show();
        }
    }

    @FXML
    void addPatient(ActionEvent event) {
        if(txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtCin.getText().isEmpty()
                || txtTele.getText().isEmpty() || txtEmail.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez remplir tout les champs !");
            alert.show();
        }else {

            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String cin = txtCin.getText();
            String email = txtEmail.getText();
            String tel = txtTele.getText();
            LocalDate date = txtDate.getValue();
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();

            Date d = new Date(year, month, day);
            Patient p = new Patient();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setCin(cin);
            p.setTelephone(tel);
            p.setEmail(email);
            p.setDate_naissance(d);

            txtNom.clear();
            txtPrenom.clear();
            txtCin.clear();
            txtTele.clear();
            txtEmail.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Patient a ete ajouter avec succes");
            alert.show();
            patientService.addPatient(p);
            loadPatients();

        }
    }

    @FXML
    void updatePatient(ActionEvent event) {
        Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
        if(patient != null){
            patient.setNom(patient.getNom());
            patient.setPrenom(patient.getPrenom());
            patient.setCin(patient.getCin());
            patient.setTelephone(patient.getTelephone());
            patient.setEmail(patient.getEmail());
            patient.setDate_naissance(patient.getDate_naissance());
            patientService.updatePatient(patient);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Patient a ete modifier avec succes");
            alert1.show();
            loadPatients();
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez selectionner un element ! !");
            alert.show();
        }
    }

    public void editPatientCols(){
        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
        colNom.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue()));

        colPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrenom.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue()));
        //colPrix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        //colPrix.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrix(e.getNewValue()));

        colCin.setCellFactory(TextFieldTableCell.forTableColumn());
        colCin.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setCin(e.getNewValue()));

        colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());
        colTelephone.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setTelephone(e.getNewValue()));

        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue()));

        colDateNaissance.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        colDateNaissance.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate_naissance(e.getNewValue()));
    }
}



