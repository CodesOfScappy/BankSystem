package org.scappy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.scappy.Models.Models;
import org.scappy.Views.AccountType;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_lfd;
    public TextField lName_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;

    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingAccountFlag = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_client_btn.setOnAction(event -> createClient());
        pAddress_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal){
                payeeAddress = createPayeAddress();
                onCreatePayeeAddress();
            }

        });
        ch_acc_box.selectedProperty().addListener((observableValue1, oldVal, newVal) -> {
            if (newVal){
                createCheckingAccountFlag = true;
            }
        });
        sv_acc_box.selectedProperty().addListener((observableValue2, oldVal, newVal) -> {
            if (newVal){
                createSavingAccountFlag = true;
            }
        });
    }


    /**
     * Methode für das Erstellen eines Clients
     */
    private void createClient(){
        // Create Checking Account
        if (createCheckingAccountFlag) {
            createAccount("Checking");
        }
        // Create Saving Account
        if (createSavingAccountFlag) {
            createAccount("Saving");
        }
        // Create Client
        String fName = fName_lfd.getText();
        String lName = lName_fld.getText();
        String password = password_fld.getText();
        Models.getInstance().getDatabaseDriver().createClient(fName,lName,payeeAddress,password, LocalDate.now());
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created Successfully");
        emptyFields();
    }

    private void createAccount(String accountType){
        double balance = Double.parseDouble(ch_amount_fld.getText());
        double balance2 = Double.parseDouble(sv_amount_fld.getText());
        // Generated Account Number
        String firstSection = "3201";
        String lastSection = Integer.toString(new Random().nextInt(9999)+1000);
        String accountNumber = firstSection+" "+lastSection;
        // Create the Checking or Saving Account
       if (accountType.equals("Checking")) {
           Models.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);
       }else{
           Models.getInstance().getDatabaseDriver().createSavingAccount(payeeAddress,accountNumber,200,balance2);
       }
    }


    /**
     * Methode zum Anzeigen der PayeeAddress im Label
     */
    private void onCreatePayeeAddress(){
        if(fName_lfd.getText() != null && lName_fld.getText() != null){
            pAddress_lbl.setText(payeeAddress);
        }
    }

    /**
     * Methode zum Erstellen der PayeeAddress
     */
    private String createPayeAddress(){
        int id = Models.getInstance().getDatabaseDriver().getLastClientsID() +1;
        char fChar = Character.toLowerCase(fName_lfd.getText().charAt(0));
        return "@"+fChar + lName_fld.getText() + id;
    }


    /**
     * Methode zum Initalisieren der Felder beim Erstellen eines Clients
     */
    private void emptyFields(){
        fName_lfd.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_acc_box.setSelected(false);
        ch_amount_fld.setText("");
        sv_acc_box.setSelected(false);
        sv_amount_fld.setText("");
    }


}
