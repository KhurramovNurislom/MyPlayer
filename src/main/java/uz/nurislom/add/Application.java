//package uz.nurislom.add;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import org.kordamp.bootstrapfx.BootstrapFX;
//import uz.nurislom.MyPlayer;
//
//public class Application {
//
//
//    Double x, y;
//    private double xOffset = 0;
//    private double yOffset = 0;
//    private static final double MIN_WIDTH = 200;
//    private static final double MIN_HEIGHT = 200;
//    private static final double MAX_WIDTH = 800;
//    private static final double MAX_HEIGHT = 800;
//
//    static AnchorPane titlePane;
//
//    public static void setTitlePane(AnchorPane titlePane) {
//        MyPlayer.titlePane = titlePane;
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(MyPlayer.class.getResource("/fxml/Player.fxml"));
//        AnchorPane root = fxmlLoader.load();
//
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
//        scene.setFill(Color.TRANSPARENT);
//
//        // Sahnaning harakatlanishini boshqarish
//        titlePane.setOnMousePressed(e -> {
//            x = e.getSceneX();
//            y = e.getSceneY();
//        });
//
//        titlePane.setOnMouseDragged(e -> {
//            stage.setX(e.getScreenX() - x);
//            stage.setY(e.getScreenY() - y);
//        });
//
//        // O'lchamini cheklangan qiymatlarda o'zgartirish
//        root.setOnMouseDragged(event -> {
//            double newWidth = event.getScreenX() - stage.getX();
//            double newHeight = event.getScreenY() - stage.getY();
//
//            if (newWidth >= MIN_WIDTH && newWidth <= MAX_WIDTH) {
//                stage.setWidth(newWidth);
//            }
//            if (newHeight >= MIN_HEIGHT && newHeight <= MAX_HEIGHT) {
//                stage.setHeight(newHeight);
//            }
//        });
//
//        stage.setResizable(true);
//        stage.initStyle(StageStyle.TRANSPARENT); // Shaffof sahna
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Assalom aleykum!.. MyPlayer");
//        launch(args);
//    }
//}
