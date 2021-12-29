module test {
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.engine;
  requires main;

  opens test to org.junit.platform.commons;
}
