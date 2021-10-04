package com.flappybird.app;
/**
 * The Floor class represents the base / floor for the Game.
 * 
 * @author Urmzd Mukhammadnaim
 */
public class Floor extends Sprite {

    /**
     * The Floor constructor which initializes the base/floor for the game.
     * @param velocity A double array which contains the (xVelocity, yVelocity) of the object.
     */
    public Floor(double[] velocity){
        super(new double[] {0, 512 - 112}, velocity, new double[]{0, 0}, "floor");
    }

    /**
     * The reset() method determines if the Floor's image will go out of bounds and 
     * resets it if it does.
     */
    public void reset(){
        double[] position = super.getPosition();
        // If iamge goes out of bounds, reset it.
        if (position[0] <= -336){
            super.setPosition(0, position[1]);
        }
    }

    /**
     * The update() method updates the position of the floor and calls the reset()
     * method if the Floor's image goes out of bounds.
     */
    @Override
    public void update(){
        reset();
        updatePosition();
    }
}
