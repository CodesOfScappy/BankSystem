package org.scappy.Controllers.Client;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.scappy.Models.Models;
import org.scappy.Models.Transaction;
import org.scappy.Views.TransactionCellFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label cheking_bal;
    public Label cheking_acc_num;
    public Label savings_bal;
    public Label saving_acc_num;
    public Label income_lbl;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;
    public Label expenses_lbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Models.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e -> new TransactionCellFactory());
        send_money_btn.setOnAction(e -> onSendMoney());
        accountSummary();
    }

    /**
     * Diese Methode wird verwendet, um die Daten an die Datenbank zu binden
     * und die Daten in der Datenbank zu aktualisieren
     *
     * @autor: CodeOfScappy
     */
    private void bindData(){
        // Bind the Client-Name to Welcome Label
        user_name.textProperty().bind(Bindings.concat("Hi,").concat(Models.getInstance().getClient().firstNameProperty()));
        // Bind aktual Date to login_date Label
        login_date.setText("Today, " + LocalDate.now());
        // Bind the Client-Data to the Dashboard
        cheking_bal.textProperty().bind(Models.getInstance().getClient().checkngAccountProperty().get().balanceProperty().asString());
        cheking_acc_num.textProperty().bind(Models.getInstance().getClient().checkngAccountProperty().get().accountNumberProperty());
        savings_bal.textProperty().bind(Models.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        saving_acc_num.textProperty().bind(Models.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }

    /**
     * Diese Methode wird verwendet, um die Liste der neuesten Transaktionen zu initialisieren
     */
    private void initLatestTransactionsList(){
        if (Models.getInstance().getLatestTransactions().isEmpty()){
            Models.getInstance().setLatestTransactions();
        }
    }


    /**
     * Diese Methode wird verwendet, um die Daten an die Datenbank zu senden
     * und die Daten in der Datenbank zu aktualisieren
     *
     * @autor: CodeOfScappy
     */
    private void onSendMoney() {
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Models.getInstance().getClient().payeeAddressProperty().get();
        ResultSet resultSet = Models.getInstance().getDatabaseDriver().searchClient(receiver);
        try {
            if (resultSet.isBeforeFirst()) {
                Models.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Vom Accounts Konto des Absenders abziehen
        Models.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");
        //Aktualisieren vom SavingAccoun im Client-Objekt
        Models.getInstance().getClient().savingsAccountProperty().get().setBalance(Models.getInstance().getDatabaseDriver().getSavingAccountBalance(sender));
        // Speichern der neues Transaction
        Models.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);
        // Felder Leeren
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");
    }

    /**
     * Diese Methode wird verwendet, um die Einnahmen und Ausgaben des Clients zu berechnen
     *
     * @autor: CodeOfScappy
     */
    public void accountSummary(){
        double income = 0;
        double expenses = 0;
        if (Models.getInstance().getAllTransactions().isEmpty()){
            Models.getInstance().setAllTransactions();
        }
        for(Transaction transaction : Models.getInstance().getAllTransactions()){
            if(transaction.senderProperty().get().equals(Models.getInstance().getClient().payeeAddressProperty().get())){
                expenses = expenses + transaction.amountProperty().get();
            }else{
                income = income + transaction.amountProperty().get();
            }
        }
        income_lbl.setText(" + $" + income);
        expenses_lbl.setText(" - $" + expenses);
    }
}
