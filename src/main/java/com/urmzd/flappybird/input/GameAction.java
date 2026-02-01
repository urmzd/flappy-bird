package com.urmzd.flappybird.input;

public sealed interface GameAction permits GameAction.Jump, GameAction.Restart {

  record Jump() implements GameAction {}

  record Restart() implements GameAction {}
}
