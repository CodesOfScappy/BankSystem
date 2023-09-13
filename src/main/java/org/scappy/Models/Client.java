package org.scappy.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Client Klasse: Diese ist für alle Client-Operationen verantwortlich
 */
public class Client {

    // Properties
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkngAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    /**
     *  Konstruktor
     * @param fName
     * @param lName
     * @param payeeAddress
     * @param cAccount
     * @param sAccount
     * @param date
     */
    public Client(String fName, String lName, String payeeAddress, Account cAccount , Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this,"FirstName",fName);
        this.lastName = new SimpleStringProperty(this,"LastName",lName);
        this.payeeAddress = new SimpleStringProperty(this,"Payee Address",payeeAddress);
        this.checkngAccount = new SimpleObjectProperty<>(this,"Checking Account",cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this,"Savidng Account",sAccount);

        this.dateCreated = new SimpleObjectProperty<>(this,"Date",date);
    }


    /**
     * Getter für firstName
     * @return firstName
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Getter für lastName
     * @return lastName
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Getter für payeeAddress
     * @return payeeAddress
     */
    public StringProperty payeeAddressProperty() {
        return payeeAddress;
    }

    /**
     * Getter für checkngAccount
     * @return checkngAccount
     */
    public Account getCheckngAccount() {
        return checkngAccount.get();
    }

    /**
     * Getter für checkingAccount
     * @return checkingAccount
     */
    public ObjectProperty<Account> checkngAccountProperty() {
        return checkngAccount;
    }

    /**
     * Getter für savingsAccount
     * @return savingsAccount
     */
    public ObjectProperty<Account> savingsAccountProperty() {
        return savingsAccount;
    }

    /**
     * Getter für dateCreated
     * @return dateCreated
     */
    public ObjectProperty<LocalDate> dateProperty() {
        return dateCreated;
    }


}
