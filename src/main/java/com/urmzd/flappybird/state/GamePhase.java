package com.urmzd.flappybird.state;

public sealed interface GamePhase permits GamePhase.Ready, GamePhase.Playing, GamePhase.GameOver {

  record Ready() implements GamePhase {}

  record Playing() implements GamePhase {}

  record GameOver(int finalScore) implements GamePhase {}
}
