package puckgame.javafx;

import javafx.scene.shape.Circle;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Puck {

    private double x;

    private double y;

    private double radius;

    private Circle c;

    public void draw() {
        c.setRadius(radius);
        c.setTranslateX(x);
        c.setTranslateY(y);
    }
    public void remove() {
        c.setRadius(0);
        c.setTranslateX(0);
        c.setTranslateY(0);
    }

}
