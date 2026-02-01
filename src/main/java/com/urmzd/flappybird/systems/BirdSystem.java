package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.GamePhase;

public interface BirdSystem {
  BirdState update(BirdState bird, GamePhase phase);

  BirdState jump(BirdState bird);

  BirdState setDead(BirdState bird);

  BirdState stop(BirdState bird);
}
