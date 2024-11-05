package uz.nurislom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerController implements Initializable {
    @FXML
    Label id_lblVolume;

    @FXML
    JFXButton id_btnFullScreen;


    @FXML
    Label id_lblTotalTime;

    @FXML
    Label id_lblLeftTime;

    @FXML
    Label id_lblCurrentTime;

    @FXML
    JFXButton id_btnNext;

    @FXML
    JFXButton id_btnPausePlay;

    @FXML
    JFXButton id_btnProtues;

    @FXML
    JFXButton id_btnSpeed;

    @FXML
    JFXButton id_btnVolume;

    @FXML
    HBox id_hbVolume;

    @FXML
    Label id_lblSpeed;

    @FXML
    MenuBar id_mbMenuBar;

    @FXML
    Menu id_menuEdit;

    @FXML
    Menu id_menuFile;

    @FXML
    Menu id_menuHelp;

    @FXML
    MediaView id_mvMediaView;

    @FXML
    JFXSlider id_sliderTime;

    @FXML
    JFXSlider id_sliderVolume;

    @FXML
    VBox id_vbMain;

    MediaPlayer mediaPlayer;

    Media media;
    double volumeTemp = 50;

    boolean atEndOfVideo = false;
    boolean isPlaying = true;
    boolean isMuted = true;
    String path = "D:\\1. Java\\3. Zako_Spring\\47_1_HomeWork.mp4";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        MyPlayer.setTitlePane(id_apTitle);
//        id_apMain.setBackground(Background.EMPTY);
        try {
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            id_mvMediaView.setMediaPlayer(mediaPlayer);

//            id_hbVolume.getChildren().remove(id_sliderVolume);

            id_lblSpeed.setText("1X");

            mediaPlayer.volumeProperty().bindBidirectional(id_sliderVolume.valueProperty());

            id_btnPausePlay.setOnAction(e -> {
                JFXButton btnPlay = (JFXButton) e.getSource();
                if (atEndOfVideo) {
                    id_sliderVolume.setValue(0);
                    atEndOfVideo = false;
                    isPlaying = false;
                }

                if (isPlaying) {
                    btnPlay.setText("play");
                    mediaPlayer.pause();
                    isPlaying = false;
                } else {
                    btnPlay.setText("pause");
                    mediaPlayer.play();
                    isPlaying = true;

                }
            });

            id_sliderVolume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(id_sliderVolume.getValue());
                    if (mediaPlayer.getVolume() != 0.0) {
                        /** shu yerda volume darajalari bilan image qo'yish kerak */
                        id_btnVolume.setText("Volume");
                        isMuted = false;
                    } else {
                        id_btnVolume.setText("Muted");
                        isMuted = true;
                    }
                }
            });


            id_lblSpeed.setOnMouseClicked(e -> {
                String speed = id_lblSpeed.getText();
                if (speed.equals("1X")) {
                    id_lblSpeed.setText("2X");
                    mediaPlayer.setRate(2.0);
                } else if (speed.equals("2X")) {
                    id_lblSpeed.setText("0.5X");
                    mediaPlayer.setRate(0.5);
                } else if (speed.equals("0.5X")) {
                    id_lblSpeed.setText("1X");
                    mediaPlayer.setRate(1);
                }
            });

            id_btnVolume.setOnMouseClicked(e -> {
                if (isMuted) {
                    id_btnVolume.setText("Volume");
                    id_sliderVolume.setValue(volumeTemp);
                    isMuted = false;
                } else {
                    id_btnVolume.setText("Mute");
                    id_sliderVolume.setValue(0.0);
                    isMuted = true;
                }
            });

            id_btnVolume.setOnMouseEntered(e -> {
                volumeTemp = id_sliderVolume.getValue();
                if (id_hbVolume.lookup("#id_sliderVolume") == null) {
                    id_hbVolume.getChildren().add(id_sliderVolume);
                    id_sliderVolume.setValue(mediaPlayer.getVolume());

                }
            });

            id_hbVolume.setOnMouseExited(e -> {
                id_hbVolume.getChildren().remove(id_sliderVolume);
            });


            id_vbMain.sceneProperty().addListener(new ChangeListener<Scene>() {
                @Override
                public void changed(ObservableValue<? extends Scene> observableValue, Scene oldScene, Scene newScene) {
                    if (oldScene == null && newScene != null) {
                        id_mvMediaView.fitHeightProperty().bind(newScene.heightProperty());
                    }
                }
            });

            id_btnFullScreen.setOnMouseClicked(e -> {
                JFXButton btnFullScreen = (JFXButton) e.getSource();
                Stage stage = (Stage) btnFullScreen.getScene().getWindow();

                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                } else stage.setFullScreen(true);

            });


            mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
                    bindCurrentTimeLabel();
                    id_sliderTime.setMax(newDuration.toSeconds());
                    id_lblTotalTime.setText(getTime(newDuration));
                }
            });


            id_sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
                    if (!isChanging) {
                        mediaPlayer.seek(Duration.seconds(id_sliderTime.getValue()));
                    }
                }
            });

            id_sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                    double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                    if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                    }

                    labelMatchEndVideo(id_lblCurrentTime.getText(), id_lblTotalTime.getText());
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
                    if (!id_sliderTime.isValueChanging()) {
                        id_sliderTime.setValue(newTime.toSeconds());
                    }
                    labelMatchEndVideo(id_lblCurrentTime.getText(), id_lblTotalTime.getText());
                }
            });

            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    id_btnPausePlay.setText("Restart");
                    atEndOfVideo = true;
                    if (!id_lblCurrentTime.textProperty().equals(id_lblTotalTime.textProperty())) {
                        id_lblCurrentTime.textProperty().unbind();
                        id_lblCurrentTime.setText(getTime(mediaPlayer.getTotalDuration()) + " / ");

                    }
                }
            });

        } catch (Exception e) {
            System.err.println("e => " + e.getMessage());
        }
    }


    private void bindCurrentTimeLabel() {
        id_lblCurrentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime()) + " / ";
            }
        }));
    }

    private String getTime(Duration time) {
        System.out.println("asd => " + time);
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (seconds > 59) hours = hours % 60;

        if (hours > 0.0) return String.format("%d:%02d:%02d",
                hours,
                minutes,
                seconds);
        else return String.format("%02d:%02d",
                minutes,
                seconds);
    }

    private void labelMatchEndVideo(String currentTime, String totalTime) {
        for (int i = 0; i < id_lblCurrentTime.getText().length(); i++) {
            if (currentTime.charAt(i) != totalTime.charAt(i)) {
                atEndOfVideo = false;
                if (isPlaying) id_btnPausePlay.setText("Pause");
                else id_btnPausePlay.setText("Play");
                break;
            } else {
                atEndOfVideo = true;
                id_btnPausePlay.setText("Restart");
            }


        }
    }

}
