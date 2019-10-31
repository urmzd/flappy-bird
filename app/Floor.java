package com.flappybird.app;

public class Floor extends Sprite {

    public Floor(){
        super(new double[] {0, 512 - 112}, new double[]{-2, 0}, new double[]{0, 0}, "floor");
    }

    public void reset(){
        double[] position = super.getPosition();
        if (position[0] <= -336){
            super.setPosition(0, position[1]);
        }
    }

    @Override
    public void update(){
        reset();
        updatePosition();
    }
}
