package org.scappy.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * Klasse für SavingsAccout
 * diese Klasse ist für alle Sparkontovorgänge verantwortlich
 *
 * @autor CodeOfScappy
 */
public class SavingAccount extends Account {
    // The withdrawal limit from a saving account
    private final DoubleProperty withdrawalLimit;

    /**
     * Konstruktor
     * @param owner
     * @param accountNumber
     * @param balance
     * @param wLimit
     */
    public SavingAccount(String owner, String accountNumber, double balance, double wLimit) {
        super(owner, accountNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(this,"Withdrawal Limit");

    }


    /**
     * Getter für withdrawalLimit
     * @return withdrawalLimit
     */
    public DoubleProperty withdrawalLimitProperty() {
        return withdrawalLimit;
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
