package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.FloorState;

public interface FloorSystem {
  FloorState update(FloorState floor);

  FloorState stop(FloorState floor);
}
