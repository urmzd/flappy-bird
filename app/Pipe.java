package com.flappybird.app;

public class Pipe extends Sprite {

    private String orientation;
    
    public Pipe(double[] position, double[] velocity, double[] acceleration, String orientation){
        super(position, velocity, acceleration, "pipe");
        this.orientation = orientation;
    }

    public void updateOrientation() {
        if (orientation.equals("down")) {
            super.setRotation(180);
        }
    }

    @Override
    public void update(){
        updateOrientation();
        updatePosition();
    }

}