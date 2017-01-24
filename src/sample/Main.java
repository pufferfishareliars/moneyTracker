package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.Transaction;
import sample.views.EditTransactionController;
import sample.views.TransactionFormController;

import java.io.IOException;

// continue from http://code.makery.ch/library/javafx-8-tutorial/part2/
// TODO - hook this up to a DB to persist the data!
public class Main extends Application {
    private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();

    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("moneyTracker");

        initRootLayout();
    }

    /**
     * Initializes the root layout
     */
    public void initRootLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/TransactionForm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(
                    this.getClass().getClassLoader().getResource("sample/assets/transaction_form_styles.css").toString()
            );
            this.primaryStage.setScene(scene);

            // Give the controller access to the main app.
            TransactionFormController controller = loader.getController();
            controller.setApp(this);

            this.primaryStage.show();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public ObservableList<Transaction> getTransactions() {
        return this.transactionData;
    }

    /**
     * Opens a dialog to edit details for the specified transaction. If the user
     * clicks OK, the changes are saved into the provided transaction object and true
     * is returned.
     *
     * @param transaction the transaction object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showTransactionEditDialog(Transaction transaction) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/EditTransactionDialog.fxml"));
            AnchorPane dialog = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditTransactionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTransaction(transaction);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
