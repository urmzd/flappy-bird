package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.PipeState;
import java.util.List;

public interface ScoreSystem {
  record ScoreResult(int score, List<PipeState> pipes) {}

  ScoreResult checkScore(BirdState bird, List<PipeState> pipes, int currentScore);
}
