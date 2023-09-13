package org.scappy.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.scappy.Views.AccountType;
import org.scappy.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


/**
 * Models Klasse: Diese Klasse ist für alle Model-Operationen verantwortlich
 *
 * @autor CodeOfScappy
 */
public class Models {
    // Elemente
    private static Models models;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    // Client Data Sektion
    //####################
    private final Client client;
    private boolean clientLoginSuccessFlag;
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;
    // Admin Data Sektion
    //######################
    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;


    /**
     * Konstruktor
     */
    private Models() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // Client Data Sektion
        this.clientLoginSuccessFlag = false;
        this.client = new Client("","","",null,null,null);
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
        // Admin Data Sektion
        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
    }


    /**
     * Singleton
     * @return models
     */
    public static synchronized Models getInstance() {
        if (models == null) {
            models = new Models();
        }
        return models;
    }

    /**
     * Getter für viewFactory
     * @return viewFactory
     */
    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    /**
     * Getter für databaseDriver
     * @return databaseDriver
     */
    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

   // Client Methoden Sektion
   //######################

    /**
     * Getter für Client-Login-Flag
     * @return client
     */
    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }

    /**
     * Setter für Client-Login-Flag
     */
    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }


    /**
     * Getter für Client
     *
     * @return client
     */
    public Client getClient() {
        return this.client;
    }


    /**
     * Client-Anmeldeinformationen auswerten.
     * Diese Methode wertet die Client-Anmeldeinformationen aus und legt die Client-Daten fest
     * @param pAddress
     * @param password
     */
    public void evaluateClientCred(String pAddress, String password){
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);
        try{
            if (resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
                this.client.dateProperty().set(date);
                checkingAccount = getCheckingAccount(pAddress);
                savingAccount = getSavingsAccount(pAddress);
                this.client.checkngAccountProperty().set(checkingAccount);
                this.client.savingsAccountProperty().set(savingAccount);
                this.clientLoginSuccessFlag = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Methode zum Vorbereiten einer Transaction und Speichern in der Datenbank
     *
     * @param limit
     *
     * @auther CodeOfScappy
     */
    private void prepareTransactions(ObservableList<Transaction> transactions,int limit) {
        ResultSet resultSet = databaseDriver.getTransactions(this.client.payeeAddressProperty().get(), limit);
        try {
            while (resultSet.next()) {
                // Get Transaction Data
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLatestTransactions(){
        prepareTransactions(this.latestTransactions,4);
    }

    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setAllTransactions(){
        prepareTransactions(this.allTransactions,-1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }





    // Admin Methoden Sektion
    //#####################

    /**
     * Getter für Admin-Login-Flag
     * @return adminLoginSuccessFlag
     */
    public boolean getAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    /**
     * Setter für Admin-Login-Flag
     */
    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag = flag;
    }


    /**
     * Administratoranmeldeinformationen auswerten.
     * Diese Methode wertet die Administratoranmeldeinformationen aus
     * @param username
     * @param password
     */
    public void evaluateAdminCred(String username, String password){
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try{
            if (resultSet.isBeforeFirst()){
                this.adminLoginSuccessFlag = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Getter für Clients
     * @return clients
     */
    public ObservableList<Client> getClients() {
        return clients;
    }


    /**
     * Methode für das Erstellen eines Clients in der Datenbank
     *
     * @auther CodeOfScappy
     */
    public void setClients(){
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try{
            while(resultSet.next()){
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
                checkingAccount = getCheckingAccount(pAddress);
                savingAccount = getSavingsAccount(pAddress);
                clients.add(new Client(fName, lName, pAddress, checkingAccount, savingAccount, date));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Methode für die Suche eines Clients in der Datenbank
     *
      * @param pAddress
     *
     * @auther CodeOfScappy
     */
    public ObservableList<Client> searchClient(String pAddress){
        ObservableList<Client> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(pAddress);
        try{
            CheckingAccount checkingAccount = getCheckingAccount(pAddress);
            SavingAccount savingAccount = getSavingsAccount(pAddress);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
            searchResult.add(new Client(fName,lName,pAddress,checkingAccount,savingAccount,date));

        }catch(Exception e){
            e.printStackTrace();
        }
        return searchResult;
    }

    // Utility Methods Section
    //#######################

    /**
     * Methode für das Erstellen eines Clients in der Datenbank
     *
     * @param pAddress
     * @auther CodeOfScappy
     */
    public CheckingAccount getCheckingAccount(String pAddress){
        CheckingAccount account = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(pAddress);
        try{
            String num = resultSet.getString("AccountNumber");
            int tLimit = resultSet.getInt("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new CheckingAccount(pAddress,num,balance,tLimit);


        }catch(SQLException e){
            e.printStackTrace();
        }
        return account;
    }


    /**
     * Methode für das Abrufen von SavingAccount Daten aus der Datenbank
     * @param pAddress
     * @return account
     *
     * @auther CodeOfScappy
     */
    public SavingAccount getSavingsAccount(String pAddress){
        SavingAccount account = null;
        ResultSet resultSet = databaseDriver.getSavingsAccountData(pAddress);
        try{
            String num = resultSet.getString("AccountNumber");
            double wLimit = resultSet.getDouble("withdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            account = new SavingAccount(pAddress,num,balance,wLimit);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return account;
    }


}
