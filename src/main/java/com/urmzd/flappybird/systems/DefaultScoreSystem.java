package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.PipeState;
import java.util.ArrayList;
import java.util.List;

public class DefaultScoreSystem implements ScoreSystem {

  @Override
  public ScoreResult checkScore(BirdState bird, List<PipeState> pipes, int currentScore) {
    if (!bird.alive() || pipes.isEmpty()) {
      return new ScoreResult(currentScore, pipes);
    }

    double birdX = bird.position().x();
    List<PipeState> updatedPipes = new ArrayList<>();
    int newScore = currentScore;

    for (PipeState pipe : pipes) {
      if (!pipe.passed() && !pipe.isTop() && pipe.position().x() < birdX) {
        updatedPipes.add(pipe.withPassed(true));
        newScore++;
      } else {
        updatedPipes.add(pipe);
      }
    }

    return new ScoreResult(newScore, updatedPipes);
  }
}
