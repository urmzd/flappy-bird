package com.urmzd.flappybird.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Vector")
class VectorTest {

  @Nested
  @DisplayName("add")
  class Add {
    @Test
    @DisplayName("should add two vectors correctly")
    void addsVectors() {
      var v1 = new Vector(1, 2);
      var v2 = new Vector(3, 4);

      var result = v1.add(v2);

      assertEquals(4, result.x());
      assertEquals(6, result.y());
    }

    @Test
    @DisplayName("should return same vector when adding ZERO")
    void addingZeroReturnsOriginal() {
      var v = new Vector(5, 10);

      var result = v.add(Vector.ZERO);

      assertEquals(v, result);
    }
  }

  @Nested
  @DisplayName("scale")
  class Scale {
    @Test
    @DisplayName("should scale vector by factor")
    void scalesVector() {
      var v = new Vector(2, 3);

      var result = v.scale(2);

      assertEquals(4, result.x());
      assertEquals(6, result.y());
    }

    @Test
    @DisplayName("should return ZERO when scaling by 0")
    void scalingByZeroReturnsZero() {
      var v = new Vector(5, 10);

      var result = v.scale(0);

      assertEquals(Vector.ZERO, result);
    }
  }

  @Nested
  @DisplayName("withX / withY")
  class With {
    @Test
    @DisplayName("withX should replace x component")
    void withXReplacesX() {
      var v = new Vector(1, 2);

      var result = v.withX(99);

      assertEquals(99, result.x());
      assertEquals(2, result.y());
    }

    @Test
    @DisplayName("withY should replace y component")
    void withYReplacesY() {
      var v = new Vector(1, 2);

      var result = v.withY(99);

      assertEquals(1, result.x());
      assertEquals(99, result.y());
    }
  }
}
