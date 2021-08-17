package com.internshala.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();
        controller.createPlayground();


        MenuBar menuBar = createMenu();
        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().addAll(menuBar);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());                                  //TO EXPAND THE MENUBAR ACROSS THE PANE (OF THE SIZE OF THE SCENE)

        Scene scene = new Scene(rootGridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect4");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private MenuBar createMenu() {
        //FILE MENU
        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("Reset");
        newGame.setOnAction(event -> {
            controller.resetGame();
        });

        MenuItem resetGame = new MenuItem("Reset");
        resetGame.setOnAction(event -> {
            controller.resetGame();
        });
        MenuItem exitGame = new MenuItem("Exit");
        exitGame.setOnAction(event -> {
            exitGame();
        });
        SeparatorMenuItem separate = new SeparatorMenuItem();
        fileMenu.getItems().addAll(newGame , resetGame, separate , exitGame);

        //HELP MENU
        Menu helpMenu = new Menu("Help");
        MenuItem aboutGame = new MenuItem("About Connect4");
        aboutGame.setOnAction(event -> {
            aboutConnect4();
        });
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> {
            aboutMe();
        });
        SeparatorMenuItem separator = new SeparatorMenuItem();
        helpMenu.getItems().addAll(aboutGame , separator , aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu , helpMenu);
        return menuBar;
    }

    private void aboutMe() {
        Alert meAlert = new Alert(Alert.AlertType.INFORMATION);
        meAlert.setTitle("About The Developer");
        meAlert.setHeaderText("Sharwari Akre");
        meAlert.setContentText("I love to play around with code and develop games." +
                               " Connect4 is one of them. I enjoy playing the game with family and friends." +
                               " I sincerely hope you will too!");
        meAlert.show();
    }

    private void aboutConnect4() {
        Alert game = new Alert(Alert.AlertType.INFORMATION);
        game.setTitle("About Connect4");
        game.setHeaderText("How To Play?");
        game.setContentText("Connect Four is a two-player connection game in which the players first choose a color and then take turns dropping colored discs" +
                            " from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down," +
                            " occupying the next available space within the column. The objective of the game is to be the first to form a horizontal," +
                            " vertical, or diagonal line of four of one's own discs. Connect Four is a solved game. The first player can always win by playing the right moves.");
        game.show();
    }

    private void exitGame() {
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("Connect4");
        exit.setHeaderText("Quit Application");
        exit.setContentText("Are you sure you want to exit the game?");
        ButtonType yesBtn = new ButtonType("Yes, I'm sure");
        ButtonType noBtn = new ButtonType("No");
        exit.getButtonTypes().setAll(yesBtn , noBtn);
        Optional<ButtonType> clicked = exit.showAndWait();
        if (clicked.isPresent() && clicked.get()==yesBtn) {
            Platform.exit();
            System.exit(0);
        }
    }

    private void resetGame() {

    }


}
