package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.FloorState;
import com.urmzd.flappybird.state.PipeState;
import java.util.List;

public interface CollisionSystem {
  boolean checkFloorCollision(BirdState bird, FloorState floor);

  boolean checkPipeCollision(BirdState bird, List<PipeState> pipes);
}
