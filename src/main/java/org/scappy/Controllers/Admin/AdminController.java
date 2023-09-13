package org.scappy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.scappy.Models.Models;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    // FXML-Elemente
    public BorderPane admin_parent;


    /**
     * Initialisiert die Admin-View
     *
     * @see Models
     * @param location URL
     * @param resources ResourceBundle
     *

     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Models.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
           switch (newValue){
               case CLIENTS -> admin_parent.setCenter(Models.getInstance().getViewFactory().getClientView());
                case DEPOSIT -> admin_parent.setCenter(Models.getInstance().getViewFactory().getDepositView());
               default -> admin_parent.setCenter(Models.getInstance().getViewFactory().getCreateClientView());
           }
        });
    }
}
