package com.urmzd.flappybird;

import com.urmzd.flappybird.input.GameAction;
import com.urmzd.flappybird.rendering.Renderer;
import com.urmzd.flappybird.state.GamePhase;
import com.urmzd.flappybird.state.GameState;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameCanvas extends Application {
  private static final long NANOS_PER_FRAME = 16_666_667L;

  private GameState state = GameState.initial();
  private final GameOrchestrator orchestrator = GameOrchestrator.createDefault();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Canvas canvas = new Canvas(GameState.WIDTH, GameState.HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    Group root = new Group(canvas);
    Scene scene = new Scene(root, GameState.WIDTH, GameState.HEIGHT);

    AtomicReference<Optional<GameAction>> pendingAction = new AtomicReference<>(Optional.empty());

    scene.setOnKeyPressed(
        e -> {
          if (e.getCode() == KeyCode.SPACE) {
            if (state.phase() instanceof GamePhase.GameOver) {
              pendingAction.set(Optional.of(new GameAction.Restart()));
            } else {
              pendingAction.set(Optional.of(new GameAction.Jump()));
            }
          } else if (state.phase() instanceof GamePhase.GameOver) {
            pendingAction.set(Optional.of(new GameAction.Restart()));
          }
        });

    new AnimationTimer() {
      private long lastUpdate = 0;

      @Override
      public void handle(long now) {
        if (now - lastUpdate >= NANOS_PER_FRAME) {
          Optional<GameAction> action = pendingAction.getAndSet(Optional.empty());
          state = orchestrator.tick(state, action);
          Renderer.render(gc, state);
          lastUpdate = now;
        }
      }
    }.start();

    stage.setTitle("Flappy Bird");
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }
}
