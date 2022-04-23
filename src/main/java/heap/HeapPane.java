package heap;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class HeapPane extends Pane {
    private final Heap<Integer> heap;
    private final double radius = 15;
    private final Color color = Color.MEDIUMPURPLE;
    private final double vGap = 40;

    public HeapPane(Heap<Integer> heap) {
        this.heap = heap;
        setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void displayHeap() {
        this.getChildren().clear();
        if (heap.size() > 0) {
            displayHeap(1, this.getWidth() / 2, vGap, this.getWidth() / 4);
        }
    }

    private void displayHeap(int i, double x, double y, double hGap) {
        if (i * 2 <= heap.size()) {
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayHeap(i * 2, x - hGap, y + vGap, hGap / 2);
        }
        if (i * 2 + 1 <= heap.size()) {
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayHeap(i * 2 + 1, x + hGap, y + vGap, hGap / 2);
        }
        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, heap.get(i) + ""));
    }

}
