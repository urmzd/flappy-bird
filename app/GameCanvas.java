package com.flappybird.app;

// IMPORTS
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The GameCanvas class opens up a window containing the graphics of the game.
 * 
 * @author Urmzd Mukhammadnaim
 */
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
