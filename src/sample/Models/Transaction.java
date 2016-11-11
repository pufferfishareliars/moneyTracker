package sample.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by elie on 05/11/16.
 */
public class Transaction {
    private final SimpleStringProperty category;
    private final SimpleDoubleProperty amount;


    public Transaction(String category, double amount) {
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleDoubleProperty(amount);
    }

    public double getAmount() {
        return amount.get();
    }

    public String getCategory() {
        return category.get();
    }


}
