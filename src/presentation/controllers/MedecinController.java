package presentation.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MedecinController {

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtPrenom;

    @FXML
    private JFXTextField tstTele;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private TableView<?> medecinsTableView;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colTelephone;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private ListView<?> consultationsListView;

    @FXML
    void addMedecin(ActionEvent event) {

    }

    @FXML
    void consultationsOfMedecin(ActionEvent event) {

    }

    @FXML
    void deleteMedecin(ActionEvent event) {

    }

}
