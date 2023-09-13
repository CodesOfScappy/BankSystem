package org.scappy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.scappy.Models.Models;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Die `ClientController`-Klasse steuert den Hauptbereich des Client-Dashboards.
 * Sie implementiert das `Initializable`-Interface, um die Initialisierung der Benutzeroberfläche zu ermöglichen.
 */
public class ClientController implements Initializable {
    // Das Hauptlayout des Client-Dashboards
    public BorderPane client_parent;


    /**
     * Diese Methode wird aufgerufen, wenn die Controller-Klasse initialisiert wird.
     *
     * @param location   Die URL des FXML-Elements.
     * @param resources  Ein Ressourcenbündel, das für die Lokalisierung verwendet werden kann.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Fügt einen Listener hinzu, um auf Änderungen der ausgewählten Menüoption zu reagieren
        Models.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            // Aktualisiert den Hauptbereich des Dashboards basierend auf der ausgewählten Option
            switch (newVal) {
                case TRANSACTIONS -> client_parent.setCenter(Models.getInstance().getViewFactory().getTransactionsView());
                case ACCOUNTS -> client_parent.setCenter(Models.getInstance().getViewFactory().getAccountsView());
                default -> client_parent.setCenter(Models.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
