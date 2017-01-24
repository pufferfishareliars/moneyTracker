package sample.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// reference is https://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_doubleermediate.htm
// TODO - add support for date
public class Transaction {
    private final SimpleStringProperty category = new SimpleStringProperty("");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(0);

    //
    // Constructors
    //

    public Transaction() {
        this("", 0); // WHY DO WE NEED THIS?
    }

    public Transaction(String category, double amount) {
        setCategory(category);
        setAmount(amount);
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

    //
    // Properties
    //
    public StringProperty categoryProperty() {
        return category;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }
}