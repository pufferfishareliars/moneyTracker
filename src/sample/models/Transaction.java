package sample.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

// reference is https://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_intermediate.htm
public class Transaction {
    private final SimpleStringProperty category = new SimpleStringProperty("");
    // TODO - WHEN SETTING THIS TO SimpleDoubleProperty, we get an error about the FXML loader not finding the amount property
    private final SimpleStringProperty amount = new SimpleStringProperty("");

    public Transaction() {
        this("", ""); // WHY DO WE NEED THIS?
    }

    public Transaction(String category, String amount) {
        setCategory(category);
        setAmount(amount);
    }

    //
    // Property binding needed for ...? something
    //

/*    public SimpleDoubleProperty amountProperty() {
        return this.amount;
    }

    public SimpleStringProperty categoryProperty() {
        return this.category;
    }*/

    //
    // Getters and Setters
    //

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getAmount() {
        return this.amount.get();
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }
}