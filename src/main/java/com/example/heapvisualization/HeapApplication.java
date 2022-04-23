package com.example.heapvisualization;

import heap.Heap;
import heap.HeapPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HeapApplication extends Application {
    Heap<Integer> heap = new Heap<>(8);

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        HeapPane heapPane = new HeapPane(heap);
        HBox hBox = new HBox(5);
        pane.setCenter(heapPane);
        pane.setTop(hBox);

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
                return;
            }
            int key;
            try {
                key = Integer.parseInt(textField.getText());
            } catch (NumberFormatException e) {
                showDialog("The value you have entered is not an integer!");
                textField.setText("");
                return;
            }
            System.out.println("Inserting " + key + " in heap with " + heap.size() + " elements");
            heap.insert(key);
            heapPane.displayHeap();
            textField.setText("");
        });

        delete.setOnMouseClicked(e -> {
            if (heap.size() == 0) {
                showDialog("Heap is empty!");
            }
            heap.extract();
            heapPane.displayHeap();
            textField.setText("");
        });
        clear.setOnMouseClicked(e -> {
            heap.clear();
            heapPane.displayHeap();
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