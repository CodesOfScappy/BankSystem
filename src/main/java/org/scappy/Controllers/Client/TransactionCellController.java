package org.scappy.Controllers.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.scappy.Models.Models;
import org.scappy.Models.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;
    public Button message_btn;



    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sender_lbl.textProperty().bind(transaction.senderProperty());
        receiver_lbl.textProperty().bind(transaction.receiverProperty());
        amount_lbl.textProperty().bind(transaction.amountProperty().asString());
        trans_date_lbl.textProperty().bind(transaction.dateProperty().asString());
        message_btn.setOnAction(e -> Models.getInstance().getViewFactory().showMessageWindow(transaction.senderProperty().get(),transaction.messageProperty().get()));
        transactionIcons();

    }

    private void transactionIcons(){
        if(transaction.senderProperty().get().equals(Models.getInstance().getClient().payeeAddressProperty().get())){
            in_icon.setFill(Color.rgb(240,240, 240));
            out_icon.setFill(Color.RED);
        }else{
            in_icon.setFill(Color.GREEN);
            out_icon.setFill(Color.rgb(240,240, 240));
        }
    }
}
