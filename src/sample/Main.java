package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Models.Transaction;
import sample.Views.TransactionForm;

public class Main extends Application {
    // TODO - how to make this responsive to window changes?
    @Override
    public void start(Stage window) throws Exception{
        window.setTitle("moneyTracker");

        //TransactionForm transactionForm = new TransactionForm();
        //Scene scene = new Scene(transactionForm.build(), 400, 300);
        Parent root = FXMLLoader.load(getClass().getResource("transaction_form.fxml"));
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("sample/assets/styles.css");

        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
