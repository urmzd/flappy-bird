package com.urmzd.flappybird.rendering;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

public final class Assets {
  private Assets() {}

  private static final Map<String, Image> cache = new HashMap<>();

  public static Image get(String name) {
    return cache.computeIfAbsent(name, n -> new Image("/" + n + ".png"));
  }

  public static Image background() {
    return get("background");
  }

  public static Image bird(int frame) {
    return get("bird" + frame);
  }

  public static Image pipe() {
    return get("pipe");
  }

  public static Image floor() {
    return get("floor");
  }

  public static Image gameover() {
    return get("gameover");
  }

  public static Image digit(int d) {
    return get(String.valueOf(d));
  }
}
