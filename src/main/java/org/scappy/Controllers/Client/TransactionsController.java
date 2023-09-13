package org.scappy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.scappy.Models.Models;
import org.scappy.Views.TransactionCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView transaction_listview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAllTransactionsList();
        transaction_listview.setItems(Models.getInstance().getAllTransactions());
        transaction_listview.setCellFactory(e -> new TransactionCellFactory());

    }


    private void initAllTransactionsList() {
        if (Models.getInstance().getAllTransactions().isEmpty()){
            Models.getInstance().setAllTransactions();
        }
    }
}
