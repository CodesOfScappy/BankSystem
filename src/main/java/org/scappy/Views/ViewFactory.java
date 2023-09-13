package org.scappy.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.scappy.Controllers.Admin.AdminController;
import org.scappy.Controllers.Client.ClientController;

import java.io.InputStream;

/**
 * Die ViewFactory-Klasse ist verantwortlich für die Erstellung und Anzeige verschiedener Ansichten
 * in der JavaFX-Anwendung.
 */
public class ViewFactory {

    private AccountType loginAccountType;
    // Client-Views
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;
    private AnchorPane depositView;


    // Admin-Views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane createClientView;
    private AnchorPane clientView;



    /**
     * Konstruktor für die ViewFactory.
     *  Hier werden die StringProperties initialisiert.
     *  Die StringProperties werden benötigt, um die ausgewählte Menüoption zu speichern.
     */
    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }


    /**
     * Gibt den Accounttypen des eingeloggten Benutzers zurück.
     * @return Der Accounttyp des eingeloggten Benutzers.
     */
    public AccountType getLoginAccountType() {
        return loginAccountType;
    }



    /**
     * Setzt den Accounttypen des eingeloggten Benutzers.
     * @param loginAccountType Der Accounttyp des eingeloggten Benutzers.
     */
    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }



    //#############################
    //#### Client-View Section ####
    //#############################
    /**
     * Gibt die ausgewählte Menüoption zurück.
     *
     * @return Die ausgewählte Menüoption als StringProperty.
     */
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }


    /**
     * Gibt die Dashboard-Ansicht zurück und lädt sie bei Bedarf aus einer FXML-Datei.
     *
     * @return Die Dashboard-Ansicht als AnchorPane.
     */
    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    /**
     * Gibt die Transactions-Ansicht zurück und lädt sie bei Bedarf aus einer FXML-Datei.
     *
     * @return Die Transactions-Ansicht als AnchorPane.
     *
     */
    public AnchorPane getTransactionsView() {
        if (transactionsView == null){
            try{
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsView;
    }


    /**
     * Gibt die Accounts-Ansicht zurück und lädt sie bei Bedarf aus einer FXML-Datei.
     *
     * @return Die Accounts-Ansicht als AnchorPane.
     *
     */
    public AnchorPane getAccountsView() {
        if (accountsView == null){
            try{
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }


    /**
     * Zeigt das Client-Fenster an, indem es die entsprechende FXML-Datei lädt und ein neues Stage-Fenster erstellt.
     *
     * @see ClientController
     */
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }


    //############################
    //#### Admin-View Section ####
    //############################
    /**
     * Gibt die ausgewählte Menüoption zurück.
     *
     * @return Die ausgewählte Menüoption als StringProperty.
     */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    /**
     * Gibt die CreateClient-Ansicht zurück und lädt sie bei Bedarf aus einer FXML-Datei.
     *
     * @return Die CreateClient-Ansicht als AnchorPane.
     *
     */
    public AnchorPane getCreateClientView() {
        if (createClientView == null){
            try{
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createClientView;
    }


    /**
     * Gibt die Client-Ansicht zurück und lädt sie bei Bedarf aus einer FXML-Datei.
     * @return Die Client-Ansicht als AnchorPane.
     */
    public AnchorPane getClientView() {
        if (clientView == null){
            try{
                clientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientView;
    }


    /**
     * Gibt die Deposit-Ansicht zurück und lädt sie bei Bedarf aus einer FXML-Datei.
     * @return Die Deposit-Ansicht als AnchorPane.
     */
    public AnchorPane getDepositView() {
        if (depositView == null){
            try{
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return depositView;
    }



    /**
     * Zeigt das Admin-Fenster an, indem es die entsprechende FXML-Datei lädt und ein neues Stage-Fenster erstellt.
     *
     * @see AdminController
     */
    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }



    /**
     * Zeigt das Login-Fenster an, indem es die entsprechende FXML-Datei lädt und ein neues Stage-Fenster erstellt.
     *
     */
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }


    /**
     * Zeigt das Fenster für die Verwendungszweck einer Üerweißung an.
     * Das Fenster ist nicht veränderbar und wird angezeigt.
     * @param pAddress
     * @param messageText
     */
    public void showMessageWindow(String pAddress, String messageText){
        StackPane pane = new StackPane();
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label message = new Label(messageText);
        hBox.getChildren().addAll(sender,message);
        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane, 300, 100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Erstellt ein neues Stage-Fenster mit der geladenen Szene und dem Titel "ATM Bank".
     * Das Fenster ist nicht veränderbar und wird angezeigt.
     *
     * @param loader Der FXMLLoader, der die Szene lädt.
     */
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        // Set the icon for the stage window
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("ATM Bank");
        stage.show();
    }


    /**
     * Schließt das Stage-Fenster.
     *
     * @param stage Das Stage-Fenster, das geschlossen werden soll.
     */
    public void closeStage(Stage stage) {
        stage.close();
    }
}
