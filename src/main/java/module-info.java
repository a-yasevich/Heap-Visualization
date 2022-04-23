module com.example.heapvisualization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.heapvisualization to javafx.fxml;
    exports com.example.heapvisualization;
}