package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception{
        window.setTitle("moneyTracker");
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Add your monthly expenses");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        // amount row
        Label amountLabel = new Label("Amount:");
        TextField amountText = new TextField();
        grid.add(amountLabel, 0, 1);
        grid.add(amountText, 1, 1);

        Label categoryLabel = new Label("Category:");
        TextField categoryText = new TextField();
        grid.add(categoryLabel, 0, 2);
        grid.add(categoryText, 1, 2);

        Button addTransactionButton = new Button("Add");
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        addTransactionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("New transaction added");
            }
        });
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addTransactionButton);
        grid.add(hbBtn, 1, 4);

        Scene scene = new Scene(grid, 400, 300);
        scene.getStylesheets().add("sample/assets/styles.css");
        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
