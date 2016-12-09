package sample.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TransactionForm {
    private GridPane grid;
    private TableView formTable;

    public TransactionForm(boolean debugMode) {
        this();
        this.grid.setGridLinesVisible(debugMode);
    }

    public TransactionForm() {
        // TODO - set defaults here
        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        formTable = new TableView();
    }

    public Parent build() {
        // which of those below should I keep as class variables?
        // is putting it in the build method proper?
        TableColumn categoryColumn = new TableColumn("category");
        TableColumn amountColumn = new TableColumn("amount");
        formTable.getColumns().addAll(categoryColumn, amountColumn);

        /*ObservableList<Transaction> items = FXCollections.observableArrayList(
                new Transaction("books", 100),
                new Transaction("food", 40.30),
                new Transaction("books", 15.67)
        );
        table.setItems(items);*/
        grid.add(formTable, 0, 5, 2, 1);

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
        // TODO - set this in a controller
        addTransactionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // validation
                String categoryString = categoryText.getText();
                Double amount;
                try {
                    amount = Double.parseDouble(amountText.getText());
                } catch (NumberFormatException badFormat) {
                    // TODO - set colors such as error, success, etc. somewhere outside of this class
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Please enter a valid amount");
                    return;
                } catch (Exception exception) {
                    return;
                }

                // also, add transaction to the list
                /*Transaction newTransaction = new Transaction(
                        categoryString,
                        amount
                );
                items.add(newTransaction);
                table.setItems(items);*/

                // TODO - have this fade out after displaying
                actiontarget.setFill(Color.DEEPSKYBLUE);
                actiontarget.setText("New transaction added");
            }
        });
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addTransactionButton);
        grid.add(hbBtn, 1, 4);

        return grid;
    }
}
