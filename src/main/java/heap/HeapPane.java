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
    private final double vGap = 40; //verticalGap - расстояние от элемента до его детей по вертикали, оно постоянно

    public HeapPane(Heap<Integer> heap) {
        this.heap = heap;
        setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void displayHeap() {
        this.getChildren().clear();
        //Если в куче есть элементы, то рекурсивно отрисовываем её, начиная с первого
        if (heap.size() > 0) {
            displayHeap(1, this.getWidth() / 2, vGap, this.getWidth() / 4);  //Расположим первый элемент
            // по центру по горизонтали, с отступом vGap по вертикали, расст. до детей по горизонтали = 1/4 экрана
        }
    }

    //Функция принимает индекс элемента в куче, его координаты x, y и расстояние до детей по горизонтали
    private void displayHeap(int i, double x, double y, double hGap) {
        double childY = y + vGap; //Координата Y ребёнка = координата Y родителя + расстояние до ребёнка по вертикали
        double childX; //Координата X ребёнка = координата X родителя +- текущее расстяние до ребёнка по горизонтали
        if (i * 2 <= heap.size()) { //Если есть левый ребёнок
            childX = x - hGap;
            getChildren().add(new Line(childX, childY, x, y)); //Нарисуем линию до ребёнка
            displayHeap(i * 2, childX, childY, hGap / 2); //Вызовем displayHeap() для индекса левого ребёнка,
                //его координат и вдвое меньшего расстояние до детей по горизонтали
        }
        if (i * 2 + 1 <= heap.size()) { //Если есть правый ребёнок
            childX = x + hGap;
            getChildren().add(new Line(childX, childY, x, y));
            displayHeap(i * 2 + 1, childX, childY, hGap / 2); //Аналогично для правого ребёнка
        }
        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        //Нарисуем узел дерева с текущим значением
        getChildren().addAll(circle, new Text(x - 4, y + 4, heap.get(i) + ""));
    }

}
