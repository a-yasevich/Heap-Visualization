package com.example.heapvisualization;

import heap.Heap;
import heap.HeapPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HeapApplication extends Application {
    private final Heap<Integer> heap = new Heap<>(8);
    private final TextField textField = new TextField();
    private final Label label = new Label("Heap is empty");

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        HeapPane heapPane = new HeapPane(heap);
        HBox hBox = new HBox(5);
        pane.setCenter(heapPane);
        pane.setTop(hBox);
        pane.setBottom(label);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Heap Visualisation");
        primaryStage.setScene(scene);

        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);

        Button insert = new Button("Insert");
        Button delete = new Button("DelMax");
        Button clear = new Button("Clear");

        hBox.getChildren().addAll(new Label("Enter an integer value"), textField, insert, delete, clear);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        insert.setOnMouseClicked(event -> {
            String value = textField.getText();
            if (value.length() == 0) {
                showDialog("You haven't entered anything!");
                label.setText("");
                return;
            }
            int element;
            try {
                element = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                showDialog("The value you have entered is not an integer!");
                label.setText("Unable to insert " + value + " in heap!");
                return;
            }
            textField.setText("");
            heap.insert(element);
            heapPane.displayHeap(); //Изменив элементы кучи, вызываем у heapPane displayHeap(), чтобы её отрисовать
            label.setText("Inserted " + element + " in heap");

        });

        delete.setOnMouseClicked(e -> {
            if (heap.size() == 0) {
                showDialog("Heap is empty!");
                label.setText("Unable to delete element from empty heap!");
                return;
            }
            int extracted = heap.extract();
            heapPane.displayHeap();
            textField.setText("");
            label.setText("Extracted " + extracted + " from heap");
        });
        clear.setOnMouseClicked(e -> {
            heap.clear();
            heapPane.displayHeap();
            label.setText("Cleared heap");
        });

        primaryStage.show();
    }

    private void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(80);
        alert.setOnCloseRequest(event -> {
            textField.setText("");
            label.setText("");
        });
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}