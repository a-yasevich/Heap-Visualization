package com.example.heapvisualization;

import heap.Heap;
import heap.HeapPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HeapApplication extends Application {
    private final Heap<Integer> heap = new Heap<>(8);

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        HeapPane heapPane = new HeapPane(heap);
        HBox hBox = new HBox(5);
        Label statusLabel = new Label("Heap is empty");
        pane.setCenter(heapPane);
        pane.setTop(hBox);
        pane.setBottom(statusLabel);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Heap Visualisation");
        primaryStage.setScene(scene);

        TextField textField = new TextField();
        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);

        Button insert = new Button("Insert");
        Button delete = new Button("DelMax");
        Button clear = new Button("Clear");

        hBox.getChildren().addAll(new Label("Enter an integer value"), textField, insert, delete, clear);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        insert.setOnMouseClicked(event -> {
            if (textField.getText().length() == 0) {
                showDialog("You haven't entered anything!");
                statusLabel.setText("");
                return;
            }
            int element;
            try {
                element = Integer.parseInt(textField.getText());
            } catch (NumberFormatException e) {
                showDialog("The value you have entered is not an integer!");
                textField.setText("");
                statusLabel.setText("");
                return;
            }
            heap.insert(element);
            heapPane.displayHeap(); //Изменив элементы кучи, вызываем у heapPane displayHeap(), чтобы её отрисовать
            textField.setText("");
            statusLabel.setText("Inserted " + element + " in heap");

        });

        delete.setOnMouseClicked(e -> {
            if (heap.size() == 0) {
                showDialog("Heap is empty!");
                statusLabel.setText("");
                return;
            }
            int extracted = heap.extract();
            heapPane.displayHeap();
            textField.setText("");
            statusLabel.setText("Extracted " + extracted + " from heap");
        });
        clear.setOnMouseClicked(e -> {
            heap.clear();
            heapPane.displayHeap();
            statusLabel.setText("Cleared heap");
        });

        primaryStage.show();
    }

    private static void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(80);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}