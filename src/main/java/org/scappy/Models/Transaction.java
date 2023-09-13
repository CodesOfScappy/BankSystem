package org.scappy.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Class for Transaction
 * This class is responsible for all the transaction operations
 */
public class Transaction {

    private final StringProperty sender;
    private final StringProperty receiver;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;


    /**
     * Constructor
     * @param sender
     * @param receiver
     * @param amount
     * @param date
     * @param message
     */
    public Transaction(String sender, String receiver, double amount, LocalDate date, String message) {
        this.sender = new SimpleStringProperty(this,"sender",sender);
        this.receiver = new SimpleStringProperty(this,"receiver",receiver);
        this.amount = new SimpleDoubleProperty(this,"amount",amount);
        this.date = new SimpleObjectProperty<>(this,"date",date);
        this.message = new SimpleStringProperty(this,"message",message);
    }

    //####################
    // Getters and Setters
    //####################

    /**
     * Getter for sender
     * @return
     */
    public StringProperty senderProperty() {
        return this.sender;
    }

    /**
     * Getter for receiver
     * @return receiver
     */
    public StringProperty receiverProperty() {
        return this.receiver;
    }

    /**
     * Getter for amount
     * @return amount
     */
    public DoubleProperty amountProperty() {
        return this.amount;
    }

    /**
     * Getter for date
     * @return date
     */
    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    /**
     * Getter for message
     * @return message
     */
    public StringProperty messageProperty() {
        return this.message;
    }
}
