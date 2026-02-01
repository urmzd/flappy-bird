module flappybird {
  requires transitive javafx.graphics;
  requires transitive javafx.base;

  opens com.urmzd.flappybird to
      javafx.base,
      javafx.graphics;
  opens com.urmzd.flappybird.state to
      javafx.base,
      javafx.graphics;
  opens com.urmzd.flappybird.systems to
      javafx.base,
      javafx.graphics;
  opens com.urmzd.flappybird.rendering to
      javafx.base,
      javafx.graphics;
  opens com.urmzd.flappybird.input to
      javafx.base,
      javafx.graphics;

  exports com.urmzd.flappybird;
  exports com.urmzd.flappybird.state;
  exports com.urmzd.flappybird.systems;
  exports com.urmzd.flappybird.rendering;
  exports com.urmzd.flappybird.input;
}
