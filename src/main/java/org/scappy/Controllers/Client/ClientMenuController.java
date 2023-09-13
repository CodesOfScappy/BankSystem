package org.scappy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.scappy.Models.Models;
import org.scappy.Views.ClientMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Die `ClientMenuController`-Klasse ist für die Steuerung des Menüs im Client-Bereich verantwortlich.
 * Sie implementiert das `Initializable`-Interface, um die Initialisierung der Benutzeroberfläche zu ermöglichen.
 */
public class ClientMenuController implements Initializable {

    // Buttons für die verschiedenen Menüoptionen
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;


    /**
     * Diese Methode wird aufgerufen, wenn die Controller-Klasse initialisiert wird.
     *
     * @param location   Die URL des FXML-Elements.
     * @param resources  Ein Ressourcenbündel, das für die Lokalisierung verwendet werden kann.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    /**
     * Fügt ActionListeners zu den Menü-Buttons hinzu.
     * Jeder Button ruft eine spezifische Aktion auf, wenn er geklickt wird.
     */
    private void addListeners(){
        dashboard_btn.setOnAction(event-> onDashboard());
        transaction_btn.setOnAction(event-> onTransactions());
        accounts_btn.setOnAction(event-> onAccounts());
        logout_btn.setOnAction(event-> onLogout());
    }


    /**
     * Diese Methode wird aufgerufen, wenn der "Accounts" Button geklickt wird.
     * Sie setzt die ausgewählte Menüoption im Modell auf "Accounts".
     */
    private void onAccounts() {
        Models.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der "Transaktionen" Button geklickt wird.
     * Sie setzt die ausgewählte Menüoption im Modell auf "Transaktionen".
     */
    private void onTransactions() {
        Models.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der "Dashboard" Button geklickt wird.
     * Sie setzt die ausgewählte Menüoption im Modell auf "Dashboard".
     */
    private void onDashboard() {
        Models.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }


    /**
     * Diese Methode wird aufgerufen, wenn der "Logout" Button geklickt wird.
     * Sie schließt das Client-Fenster und zeigt das Login-Fenster an.
     */
    private void onLogout(){
        // Get Stage
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        // Close the Client Window
        Models.getInstance().getViewFactory().closeStage(stage);
        // Schow Login Window
        Models.getInstance().getViewFactory().showLoginWindow();
        // Set the ClientSuccessFlag to Flase
        Models.getInstance().setClientLoginSuccessFlag(false);
    }



}