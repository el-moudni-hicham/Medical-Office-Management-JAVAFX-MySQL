package presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private Parent fxml;
    @FXML
    private BorderPane root;

    @FXML
    void consultations(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../view/consultationView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void medecins(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../view/medecinView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void patients(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../view/patientView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
