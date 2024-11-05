package uz.nurislom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Objects;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyPlayer extends Application {

    static double x;
    static double y;
    static AnchorPane titlePane;

    public static void setTitlePane(AnchorPane titlePane) {
        MyPlayer.titlePane = titlePane;
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MyPlayer.class.getResource("/fxml/Player.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        BootstrapFX.bootstrapFXStylesheet(),
        scene.getStylesheets().addAll(
                Objects.requireNonNull(MyPlayer.class.getResource("/css/Style.css")).toExternalForm());


//        scene.setFill(Color.TRANSPARENT);

//        stage.getIcons().add(new Image("/images/icon.png"));

//        titlePane.setOnMousePressed(e -> {
//            x = e.getSceneX();
//            y = e.getSceneY();
//        });
//
//        titlePane.setOnMouseDragged(e -> {
//            stage.setX(e.getScreenX() - x);
//            stage.setY(e.getScreenY() - y);
//        });


        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(null);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Assalom aleykum!.. MyPlayer");
        launch(args);
    }

}