package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.FloorState;
import com.urmzd.flappybird.state.PipeState;
import com.urmzd.flappybird.state.Vector;
import java.util.List;

public class DefaultCollisionSystem implements CollisionSystem {

  private static final double BIRD_WIDTH = 34;
  private static final double BIRD_HEIGHT = 24;
  private static final double PIPE_WIDTH = 52;
  private static final double PIPE_HEIGHT = 320;

  @Override
  public boolean checkFloorCollision(BirdState bird, FloorState floor) {
    double birdBottom = bird.position().y() + BIRD_HEIGHT;
    return birdBottom >= floor.position().y();
  }

  @Override
  public boolean checkPipeCollision(BirdState bird, List<PipeState> pipes) {
    Vector birdPos = bird.position();

    for (PipeState pipe : pipes) {
      if (intersects(
          birdPos.x(),
          birdPos.y(),
          BIRD_WIDTH,
          BIRD_HEIGHT,
          pipe.position().x(),
          pipe.position().y(),
          PIPE_WIDTH,
          PIPE_HEIGHT)) {
        return true;
      }
    }

    return false;
  }

  private boolean intersects(
      double x1, double y1, double w1, double h1, double x2, double y2, double w2, double h2) {
    return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
  }
}
