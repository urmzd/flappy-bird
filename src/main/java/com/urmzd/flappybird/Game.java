package com.urmzd.flappybird;

// IMPORTS.
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Game class handles the internal logic of the Flappy Bird game.
 * 
 * @author Urmzd Mukhammadnaim
 */
public class Game {

    // CONSTANTS.
    private final int HEIGHT = 512;
    private final int WIDTH = 288;
    private final int READY = 0;
    private final int START = 1;
    private final int FINISH = 2;

    // ATTRIBUTES.
    private Group root;
    private Canvas canvas;
    private Scene scene;
    private Stage stage;
    private GraphicsContext gc;
    private Bird bird;
    private Sprite bg;
    private Sprite gameover;
    private Floor floor;
    Sprite score0;
    Sprite score1;
    private ArrayList<Pipe> pipes;
    private int state;
    private int score;

    /**
     * The Game constructor initializes all the objects needed to open up a window
     * containing
     * the graphics of the game.
     */
    public Game() {
        this.root = new Group();
        this.scene = new Scene(root, WIDTH, HEIGHT);
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.setTitle("Flappy Bird");
        this.stage.setResizable(false);
        this.state = READY;
        this.score = 0;
    }

    /**
     * The setSprites() method sets all the sprites necessary for the game to run.
     */
    public void setSprites() {
        this.bg = new Sprite(new double[] { 0, 0 }, "background");
        this.gameover = new Sprite(new double[] { (WIDTH - 192) / 2, HEIGHT / 2 - 42 }, "gameover");
        this.bird = new Bird(new double[] { 74, 200 }, new double[] { 0, 0 }, new double[] { 0, 0 });
        this.score0 = new Sprite(new double[] { (WIDTH - 24) / 2, 60 }, "0");
        this.score1 = new Sprite(new double[] { (WIDTH + 24) / 2, 60 }, "0");
        this.floor = new Floor(new double[] { -2, 0 });
        this.pipes = new ArrayList<>(); // Will later contain sprites.
    }

    /**
     * The renderBackground() method clears the current canvas and renders the
     * background image.
     */
    public void renderBackground() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        bg.render(gc);
    }

    // Return stage containing the canvas.
    public Stage getStage() {
        return this.stage;
    }

    /**
     * The start() method handles the game loop and keyboard input.
     */
    public void start() {

        // Set the sprites.
        setSprites();

        // Determine what the keyboard input does.
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (state == READY && key.getCode() == KeyCode.SPACE) {
                state = START;
                bird.jump();
            } else if (state == START && key.getCode() == KeyCode.SPACE) {
                bird.jump();
            } else {
                state = READY;
                score = 0;
                setSprites();
            }
        });

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        // Game loop.
        KeyFrame kf = new KeyFrame(Duration.seconds(0.016), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                renderBackground();
                renderPipes();
                renderBird();
                renderFloor();
                renderGameOver();
                renderScore();
                checkPipeCollision();
                checkFloorCollision();
            }
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

    }

    /**
     * The checkScore() method determines if bird has passed a set of pipes and
     * increments the score, in
     * addition to updating the sprites.
     */
    public void checkScore() {

        if (bird.isAlive() && bird.getPosition()[0] == pipes.get(0).getPosition()[0]) {
            score++;
        }

        score0.updateImage(String.valueOf(score % 10));
        score1.updateImage(String.valueOf(score / 10));
    }

    /**
     * The renderScore() method determines the placement of the score sprites and
     * renders them.
     */
    public void renderScore() {

        if (state == START || state == FINISH) {
            checkScore();

            if (score >= 10) {
                if (score1.getPosition()[0] != (WIDTH - 24) / 2) {
                    score1.setPosition((WIDTH - 36) / 2, 60);
                    score0.setPosition((WIDTH + 16) / 2, 60);
                }
                score1.render(gc);
            }

            score0.render(gc);
        }

    }

    /**
     * The renderGameOver() method renders the gameover sprite.
     */
    public void renderGameOver() {
        if (state == FINISH) {
            gameover.render(gc);
        }
    }

    /**
     * The renderBird() method updates the position of the bird and renders the
     * sprite.
     */
    public void renderBird() {
        bird.update();
        bird.render(gc);
    }

    /**
     * The renderPipes() method updates the position and renders all the sprites
     * present within the pipes arraylist.
     */
    public void renderPipes() {

        if (state == START || state == FINISH) {
            movePipes();

            for (Pipe pipe : pipes) {
                pipe.update();
                pipe.render(gc);
            }
        }

    }

    /**
     * The renderFloor() method updates the position of the floor and renders the
     * sprite.
     */
    public void renderFloor() {
        floor.update();
        floor.render(gc);
    }

    /**
     * The checkFloorCollision() method checks if the bird has the floor and if it
     * has,
     * it stops the game.
     */
    public void checkFloorCollision() {
        if (state == START || state == FINISH) {
            if (bird.intersects(floor)) {
                bird.setDead();
                bird.setListLess();
                stopPipes();
                bird.stop();
                floor.stop();
                state = FINISH;
            }
        }
    }

    /**
     * The checkFloorColliision() method checks if the bird has hit a pipe and if it
     * has,
     * it plummets the bird to ground, in addition to stopping the movement of the
     * pipes and
     * floor.
     */
    public void checkPipeCollision() {
        if (state == START || state == FINISH) {
            for (Pipe pipe : pipes) {
                if (pipe.intersects(bird)) {
                    bird.setDead();
                    stopPipes();
                    floor.stop();
                    state = FINISH;
                }
            }
        }
    }

    /**
     * The stopPipes() method stops all the pipes.
     */
    public void stopPipes() {
        for (Pipe pipe : pipes) {
            pipe.stop();
        }
    }

    /**
     * The movePipes() method adds pipes and remove pipes depending on the scenerio.
     */
    public void movePipes() {
        addPipes();
        removePipes();
    }

    /**
     * The addPipes() method adds pipes to the game if no pipes are present or
     * if a set of pipes have reached the middle of the canvas.
     */
    public void addPipes() {
        double height = getRandom();

        Pipe up = new Pipe(new double[] { 288, 80 + height }, new double[] { floor.getVelocity()[0], 0 },
                new double[] { 0, 0 }, "up");
        Pipe down = new Pipe(new double[] { 288, -320 + height }, new double[] { floor.getVelocity()[0], 0 },
                new double[] { 0, 0 }, "down");

        if (pipes.size() == 0) {
            pipes.add(up);
            pipes.add(down);
        }

        double xPosition = pipes.get(0).getPosition()[0];

        if (xPosition == WIDTH / 2 - 52) {
            pipes.add(up);
            pipes.add(down);
        }

    }

    /**
     * The removePipes() method removes pipes from the arraylist if it
     * is off screen.
     */
    public void removePipes() {
        double xPosition = pipes.get(0).getPosition()[0];

        if (xPosition <= -52) {
            pipes.remove(0);
            pipes.remove(0);
        }

    }

    /**
     * The getRandom() method generates a random double and return's it value.
     * 
     * @return A double with a minimum value of 50.
     */
    public double getRandom() {
        Random random = new Random();
        return (random.nextDouble() * 200) + 50;
    }

}
