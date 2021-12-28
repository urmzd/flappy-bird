package com.urmzd.flappybird;

/**
 * The Pipe class is to represent a Pipe object (an obstacle) in the Game.
 */
public class Pipe extends Sprite {

    // ATTRIBUTES.
    private String orientation;

    public Pipe(double[] position, double[] velocity, double[] acceleration, String orientation) {
        super(position, velocity, acceleration, "pipe");
        this.orientation = orientation;
    }

    /**
     * The updateOrientation() method determines how to orientate the Pipe image.
     */
    public void updateOrientation() {
        if (orientation.equals("down")) {
            super.setRotation(180);
        }
    }

    /**
     * The update() method updates the Pipe image in addition to its position.
     */
    @Override
    public void update() {
        updateOrientation();
        updatePosition();
    }

}
