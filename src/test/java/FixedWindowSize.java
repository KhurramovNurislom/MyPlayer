import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class FixedWindowSize extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane p = new Pane();
        p.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        p.setMinSize(60, 60); //Make this go out beyond window bounds.
        Scene s = new Scene(p);

        primaryStage.setScene(s);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        //This doesn't work
        //primaryStage.setMinWidth(1280);
        //primaryStage.setMaxWidth(1280);
        //primaryStage.setMinHeight(800);
        //primaryStage.setMaxHeight(800);



        primaryStage.show();
    }

}