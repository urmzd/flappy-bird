package com.urmzd.flappybird.systems;

public final class DifficultyConfig {

  private DifficultyConfig() {}

  public static double pipeSpeed(int score) {
    return -(1.5 + 2.0 * (1 - Math.exp(-score / 40.0)));
  }

  public static double gapSize(int score) {
    return 100.0 - 35.0 * (1 - Math.exp(-score / 35.0));
  }
}
