package org.scappy.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *  Account Klasse: Diese Klasse ist für alle Account-Operationen verantwortlich
 *  @autor CodeOfScappy
 */
public abstract class Account {

    // Elemente
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;

    /**
     * Konstruktor
     *
     * @param owner
     * @param accountNumber
     * @param balance
     */
    public Account(String owner,String accountNumber, double balance) {
        this.owner = new SimpleStringProperty(this,"Owner",owner);
        this.accountNumber = new SimpleStringProperty(this,"Account Number",accountNumber);
        this.balance = new SimpleDoubleProperty(this,"Balance",balance);
    }


    /**
     * Getter für owner
     * @return owner
     */
    public StringProperty ownerProperty() {
        return owner;
    }

    /**
     * Getter für accountNumber
     * @return accountNumber
     */
    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    /**
     * Getter für balance
     * @return balance
     */
    public DoubleProperty balanceProperty() {
        return balance;
    }


    /**
     * Getter für balance
     * @return balance
     */
    public void setBalance(double balance){
        this.balance.set(balance);
    }


}
