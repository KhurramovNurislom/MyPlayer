package uz.nurislom.add;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Loader {
    public static void main(String[] args) {
        AnchorPane ap = new AnchorPane();

        ProgressIndicator pi = new ProgressIndicator();
        ProgressIndicator pii = new ProgressIndicator();
        ProgressIndicator piii = new ProgressIndicator();
        ProgressIndicator piiii = new ProgressIndicator();
        AnchorPane.setRightAnchor(pi, 0.0);
        AnchorPane.setBottomAnchor(piii, 0.0);
        AnchorPane.setRightAnchor(piiii, 0.0);
        AnchorPane.setBottomAnchor(piiii, 0.0);
        AnchorPane.setLeftAnchor(piii, 0.0);
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setFill(Color.RED);
        AnchorPane.setBottomAnchor(circle, 210.0);
        AnchorPane.setTopAnchor(circle, 210.0);
        AnchorPane.setLeftAnchor(circle, 210.0);
        AnchorPane.setRightAnchor(circle, 210.0);
        ap.getChildren().addAll(pi,pii,piii,circle,piiii);

    }
}
