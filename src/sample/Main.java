package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.Transaction;
import sample.models.TransactionDataList;
import sample.views.EditTransactionController;
import sample.views.TransactionFormController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

// continue from http://code.makery.ch/library/javafx-8-tutorial/part2/
// TODO - fix some of the UI
// TODO - ask if we want to save data when exiting
// TODO - add support for dates
public class Main extends Application {
    private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();

    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("moneyTracker");

        initRootLayout();
        retrieveExistingData();
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

    public void retrieveExistingData() {
        // Try to load last opened person file.
        File file = getTransactionFilePath();
        if (file != null) {
            loadTransactionDataFromFile(file);
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


    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setTransactionFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Transactions - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("MoneyTrackerApp");
        }
    }

    /**
     * Returns the file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getTransactionFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Saves the current transaction data to the specified file.
     *
     * @param file
     */
    public void saveTransactionDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TransactionDataList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            TransactionDataList wrapper = new TransactionDataList();
            wrapper.setTransactions(this.transactionData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setTransactionFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
    public void loadTransactionDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TransactionDataList.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            TransactionDataList wrapper = (TransactionDataList) um.unmarshal(file);

            transactionData.clear();
            transactionData.addAll(wrapper.getTransactions());

            // Save the file path to the registry.
            setTransactionFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
}
