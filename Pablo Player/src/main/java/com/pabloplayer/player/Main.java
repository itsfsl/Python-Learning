package com.pabloplayer.player;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.IOException;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    //private String app_logo_dir = "file:/C:/users/moham/desktop/Pablo Player/src/main/resources/assets/logos/app_logo.png";
    private String app_logo_dir = "file:/C:\\Users\\direc\\Desktop\\Pablo Player\\src\\main\\resources\\assets\\logos\\app_logo.png";
    private Image app_logo = new Image(app_logo_dir);

    @Override
    public void start(Stage stage) throws IOException {

        /* Parent root = FXMLLoader.load(getClass().getResource("app_design.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("app_design.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Pablo Player");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(app_logo);
        stage.initStyle(StageStyle.TRANSPARENT); */

        Parent root = FXMLLoader.load(getClass().getResource("app_design.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(this.getClass().getResource("app_design.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(app_logo);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {

                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}