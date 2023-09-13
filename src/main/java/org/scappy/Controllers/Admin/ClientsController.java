package org.scappy.Controllers.Admin;


import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.scappy.Models.Client;
import org.scappy.Models.Models;
import org.scappy.Views.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    public ListView<Client> clients_listview;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClientList();
        clients_listview.setItems(Models.getInstance().getClients());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }


    /**
     * Methode zum Initialisieren der Daten
     */
    private void initClientList(){
        //TODO: Datenbankabfrage
        if (Models.getInstance().getClients().isEmpty()){
            Models.getInstance().setClients();
        }
    }
}
