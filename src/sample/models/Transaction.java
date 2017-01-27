package sample.models;

import javafx.beans.property.*;

import java.util.Date;

public class Transaction {
    private final SimpleStringProperty category = new SimpleStringProperty("");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(0);
    // TODO - confirm if we nee default property
    private final SimpleObjectProperty<Date> dateAdded = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Date> dateMade = new SimpleObjectProperty<>();

    //
    // Constructors
    //

    public Transaction() {
        this("", 0, new Date(), new Date()); // WHY DO WE NEED THIS?
    }

    public Transaction(String category, double amount, Date dateAdded, Date dateMade) {
        setCategory(category);
        setAmount(amount);
        setDateAdded(dateAdded);
        setDateMade(dateMade);
    }

    //
    // Getters and Setters
    //

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public double getAmount() {
        return this.amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public Date getDateAdded() {
        return this.dateAdded.get();
    }


    public void setDateAdded(Date dateAdded) {
        this.dateAdded.set(dateAdded);
    }

    public Date getDateMade() {
        return this.dateMade.get();
    }

    public void setDateMade(Date dateMade) {
        this.dateMade.set(dateMade);
    }


    //
    // Properties
    //
    public StringProperty categoryProperty() {
        return category;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public ObjectProperty<Date> dateAddedProperty() {
        return dateAdded;
    }

    public ObjectProperty<Date> dateMadeProperty() {
        return dateMade;
    }
}