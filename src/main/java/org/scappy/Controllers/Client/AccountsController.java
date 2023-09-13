package org.scappy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    // ch = checking, sv = savings
    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_balance;
    public Label sv_acc_num;
    public Label sv_acc_limit;
    public Label sv_acc_date;
    public Label sv_acc_balance;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_ch;
    public Button trans_to_ch_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}