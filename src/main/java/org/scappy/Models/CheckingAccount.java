package org.scappy.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Klasse für Girokonto
 * diese Klasse ist für alle Girokontovorgänge verantwortlich
 *
 * @autor CodeOfScappy
 */
public class CheckingAccount extends Account {

    // The number of transactions a client is allowed to do per day
    private final IntegerProperty transactionLimit;

    /**
     * Konstruktor
     * @param owner
     * @param accountNumber
     * @param balance
     * @param tLimit
     */
    public CheckingAccount(String owner, String accountNumber, double balance, int tLimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this,"Transaction Limit",tLimit);
    }

    /**
     * Getter für transactionLimit
     * @return transactionLimit
     */
    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }


    /**
     * Überschreiben der toString Methode
     * für die Darstellung der Client-List
     *
     * @return accountNumber
     *
     * @autor CodeOfScappy
     */
    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
