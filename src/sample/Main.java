package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // TODO - how to make this responsive to window changes?
    @Override
    public void start(Stage window) throws Exception{
        window.setTitle("moneyTracker");

        //TransactionForm transactionForm = new TransactionForm();
        //Scene scene = new Scene(transactionForm.build(), 400, 300);
        Parent root = FXMLLoader.load(getClass().getResource("transaction_form.fxml"));
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(
                this.getClass().getClassLoader().getResource("sample/assets/transaction_form_styles.css").toString()
        );

        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
