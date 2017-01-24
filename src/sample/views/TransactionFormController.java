package sample.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;
import sample.models.Transaction;

// TODO - add support for date
// TODO - add support for pressing enter on the keyboard for submit
public class TransactionFormController {
    @FXML
    Text resultMessage, transactionOutput;
    @FXML
    TextField amount, category;
    @FXML
    TableView<Transaction> transactionTable;

    private Main app;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        // add to the table
        System.out.printf("amount = %s\ncategory = %s\n", amount.getText(), category.getText());
        // TODO - add validation for the parseDouble() call
        Transaction inputTransaction = new Transaction(category.getText(), Double.parseDouble(amount.getText()));
        transactionTable.getItems().add(inputTransaction);

        // display an output message
        transactionOutput.setText(category.getText() + " - $" + amount.getText());
        resultMessage.setText("Transaction added");

        // set the fields back to nothing
        amount.setText("");
        category.setText("");
    }

    // TODO
    @FXML
    protected void saveTransactionTable(ActionEvent event) {

    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditTransaction() {
        Transaction selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            boolean okClicked = app.showTransactionEditDialog(selectedTransaction);
            if (okClicked) {
                // take whatever actions you'd like...none for now
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transaction Selected");
            alert.setContentText("Please select a transaction in the table.");

            alert.showAndWait();
        }
    }

    public void setApp(Main app) {
        this.app = app;

        // Add observable list data to the table
        transactionTable.setItems(this.app.getTransactions());
    }
}
