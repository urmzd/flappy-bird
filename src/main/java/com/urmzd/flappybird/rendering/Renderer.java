package com.urmzd.flappybird.rendering;

import com.urmzd.flappybird.state.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public final class Renderer {
  private Renderer() {}

  private static final int WIDTH = GameState.WIDTH;
  private static final int HEIGHT = GameState.HEIGHT;

  public static void render(GraphicsContext gc, GameState state) {
    gc.clearRect(0, 0, WIDTH, HEIGHT);

    renderBackground(gc);
    renderPipes(gc, state);
    renderBird(gc, state.bird());
    renderFloor(gc, state.floor());
    renderScore(gc, state);
    renderGameOver(gc, state);
  }

  private static void renderBackground(GraphicsContext gc) {
    gc.drawImage(Assets.background(), 0, 0);
  }

  private static void renderBird(GraphicsContext gc, BirdState bird) {
    Image birdImage = Assets.bird(bird.animationFrame());
    double x = bird.position().x();
    double y = bird.position().y();
    double rotation = bird.rotation();

    gc.save();
    applyRotation(gc, rotation, x + birdImage.getWidth() / 2, y + birdImage.getHeight() / 2);
    gc.drawImage(birdImage, x, y);
    gc.restore();
  }

  private static void renderPipes(GraphicsContext gc, GameState state) {
    Image pipeImage = Assets.pipe();

    for (PipeState pipe : state.pipes()) {
      double x = pipe.position().x();
      double y = pipe.position().y();

      gc.save();
      if (pipe.isTop()) {
        applyRotation(gc, 180, x + pipeImage.getWidth() / 2, y + pipeImage.getHeight() / 2);
      }
      gc.drawImage(pipeImage, x, y);
      gc.restore();
    }
  }

  private static void renderFloor(GraphicsContext gc, FloorState floor) {
    gc.drawImage(Assets.floor(), floor.position().x(), floor.position().y());
  }

  private static void renderScore(GraphicsContext gc, GameState state) {
    if (!(state.phase() instanceof GamePhase.Playing)
        && !(state.phase() instanceof GamePhase.GameOver)) {
      return;
    }

    int score = state.score();
    int ones = score % 10;
    int tens = score / 10;

    if (score >= 10) {
      double x1 = (WIDTH - 36) / 2.0;
      double x0 = (WIDTH + 16) / 2.0;
      gc.drawImage(Assets.digit(tens), x1, 60);
      gc.drawImage(Assets.digit(ones), x0, 60);
    } else {
      double x = (WIDTH - 24) / 2.0;
      gc.drawImage(Assets.digit(ones), x, 60);
    }
  }

  private static void renderGameOver(GraphicsContext gc, GameState state) {
    if (state.phase() instanceof GamePhase.GameOver) {
      Image gameoverImage = Assets.gameover();
      double x = (WIDTH - gameoverImage.getWidth()) / 2.0;
      double y = HEIGHT / 2.0 - gameoverImage.getHeight() / 2.0;
      gc.drawImage(gameoverImage, x, y);
    }
  }

  private static void applyRotation(
      GraphicsContext gc, double angle, double pivotX, double pivotY) {
    Rotate r = new Rotate(angle, pivotX, pivotY);
    gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
  }
}
