package com.flappybird.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameCanvas extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override 
    public void start(Stage stage){
        Game game = new Game();
        stage = game.getStage(); 
        game.start(); 
        stage.show();
    }
}   