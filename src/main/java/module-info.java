module main {
  requires transitive javafx.graphics;
  requires transitive javafx.base;

  opens com.urmzd.flappybird to javafx.base, javafx.graphics;

  exports com.urmzd.flappybird;
}
