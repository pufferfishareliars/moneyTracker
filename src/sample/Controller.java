package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    Text resultMessage, transactionOutput;
    @FXML
    TextField amount, category;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        transactionOutput.setText(category.getText() + " - $" + amount.getText());
        resultMessage.setText("Transaction added");
    }
}
