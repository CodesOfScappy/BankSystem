package org.scappy.Controllers.Admin;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.scappy.Models.Client;
import org.scappy.Models.Models;
import org.scappy.Views.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {


    public TextField pAddress_fls;
    public Button search_btn;
    public ListView<Client> result_listview;
    public TextField amount_fld;
    public Button deposit_btn;

    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_btn.setOnAction(e -> onClientSearch());
        deposit_btn.setOnAction(e -> onDeposit());


    }

    private void onClientSearch(){
        ObservableList<Client> searchResult = Models.getInstance().searchClient(pAddress_fls.getText());
        result_listview.setItems(searchResult);
        result_listview.setCellFactory(e -> new ClientCellFactory());
        client = searchResult.get(0);
    }

    private void onDeposit(){
        double amout = Double.parseDouble(amount_fld.getText());
        double newBalance = amout + client.savingsAccountProperty().get().balanceProperty().get();
        if(amount_fld.getText() != null){
            //TODO: Checking the 2.Parameters from the (DatabaseDriver.depositSavings() Method) !!
            Models.getInstance().getDatabaseDriver().depositSavings(client.payeeAddressProperty().get(),newBalance);
        }
        emptyFields();
    }

    private void emptyFields(){
        pAddress_fls.clear();
        amount_fld.clear();
    }
}
