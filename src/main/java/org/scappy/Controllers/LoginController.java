package org.scappy.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.scappy.Models.Models;
import org.scappy.Views.AccountType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller für das Login-Fenster
 * diese Klasse ist für die Steuerung des Login-Fensters verantwortlich
 *
 * @see Initializable
 * @see Models
 * @see AccountType
 *
 * @autor CodeOfScappy
 */
public class LoginController implements Initializable {
    // Elemente des Login-Fensters
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_adress_lbl;
    public TextField payee_adress_field;
    public TextField password_field;
    public Button login_btn;
    public Label error_lbl;


    /**
     * Initialisierungsmethode:
     * Diese Methode ist für die Initialisierung des Login-Fensters verantwortlich.
     * Sie initialisiert die Auswahlbox für die Kontotypen und setzt den Standardwert auf CLIENT.
     * Außerdem wird ein Listener für die Auswahlbox gesetzt, der die Auswahl des Kontotyps speichert.
     * Des Weiteren wird ein Listener für die Anmeldeschaltfläche gesetzt, der die Anmeldeaktion ausführt.
     *
     * @see Models#getViewFactory()
     * @see Models#evaluateClientCred(String, String)
     * @see Models#evaluateAdminCred(String, String)
     *
     * @param location
     * @param resources
     *
     * @autor CodeOfScappy
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Models.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(event -> onLogin());
    }



    /**
     * Aktion der Anmeldeschaltfläche:
     * Diese Methode ist für die Aktion der Anmeldeschaltfläche verantwortlich.
     * Sie wertet die Anmeldeinformationen aus und zeigt das entsprechende Fenster an
     *
     * @see Models#evaluateClientCred(String, String)
     * @see Models#evaluateAdminCred(String, String)
     * @see Models#getClientLoginSuccessFlag()
     * @see Models#getAdminLoginSuccessFlag()
     * @see Models#getViewFactory()
     *
     * @author CodeOfScappy
     */
    private void onLogin(){
        System.out.println("Login Button Clicked");
        // Error-Label wird ausgeblendet
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if (Models.getInstance().getViewFactory().getLoginAccountType()== AccountType.CLIENT){
            // Auswerten der Anmeldeinformationen für den Client
            Models.getInstance().evaluateClientCred(payee_adress_field.getText(),password_field.getText());
            if (Models.getInstance().getClientLoginSuccessFlag()){
                Models.getInstance().getViewFactory().showClientWindow();
                // Schließen des Login-Fensters
                Models.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_adress_field.setText("");
                password_field.setText("");
                error_lbl.setText("Invalid Client Login Credentials");
            }
        } else {
            // Auswerten der Anmeldeinformationen für den Administrator
            Models.getInstance().evaluateAdminCred(payee_adress_field.getText(),password_field.getText());
            if (Models.getInstance().getAdminLoginSuccessFlag()){
                Models.getInstance().getViewFactory().showAdminWindow();
                // Schließen des Login-Fensters
                Models.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_adress_field.setText("");
                password_field.setText("");
                error_lbl.setText("Invalid Admin Login Credentials");
            }
        }
    }

    /**
     * Setzt den Kontotyp
     * diese Methode ist für das Setzen des Kontotyps verantwortlich.
     * Sie wird von der Auswahlbox aufgerufen und setzt den Kontotyp entsprechend.
     *
     * @see AccountType
     * @see Models#getViewFactory()
     *
     * @autor CodeOfScappy
     */
    private void setAcc_selector() {
        Models.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        // Ändern des Labels für die Payee-Adresse
        if (acc_selector.getValue()== AccountType.ADMIN){
            payee_adress_lbl.setText("Username:");
        } else {
            payee_adress_lbl.setText("Payee Address:");
        }
    }

}
