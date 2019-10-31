package com.flappybird.app;

public class Bird extends Sprite{

    // CONSTANTS.
    private final double MAX_ACCELERATION = 0.1;
    private final double JUMP_VELOCITY = -2.5;
    private final double JUMP_ROTATION = -25;
    private final double ROTATION_DELAY = 10;
    private final double DEAD_VELOCITY = 6;
    private final int TYPE_DELAY = 10;

    // ATTRIBUTES.
    private int type;
    private double idle_time;
    private double type_time;
    private double rotation_time; 
    private boolean alive;
    private boolean active;

    /**
     * The Bird constructor is meant to initialize a Bird object (player).
     * in the Game.
     * @param position A double array containing the (x,y) coordinates of the Bird object.
     * @param velocity A double array containing the (xVelocity, yVelocity) of the Bird object.
     * @param acceleration A double array containing the (xAcceleration, yAcceleration) of the Bird object.
     */
    public Bird(double[] position, double[] velocity, double[] acceleration) {
        super(position, velocity, acceleration, "bird0");
        alive = true;
        active = false;
        type_time = 0;
        idle_time = 0;
        rotation_time = 0;
    }

    /**
     * The update() method determines the status of the Bird object and updates it position accordingly.
     */
    @Override
    public void update(){

        // If the bird is alive but not active (ready to play), let the bird be idle.
        if(alive && !active){
            idle();
        }

        // If the bird is alive and active (ready to play), update its rotation and velocity values.
        if(alive && active){
            double yAcceleration = super.getAcceleration()[1];

            rotation_time++; // Count the number of iterations since last jump rotation.
            
            // If acceleration is less than max acceleration, increment acceleration.
            if(yAcceleration <= MAX_ACCELERATION){
                super.updateAcceleration(0, 0.006);
            }
            
            // If rotation time exceeds the rotation delay time, increment rotation value.
            if(rotation_time >= ROTATION_DELAY){
                if(super.getRotation() <= 90){
                    super.updateRotation(yAcceleration * 17);
                }
            }
        }
        
        updateType(); // Change Bird image.
        super.updateVelocity(); // Update velocity.
        super.updatePosition(); // Update position.
    }

    /**
     * The idle() method oscillates the bird in the vertical plane.
     */
    public void idle(){
        super.setVelocity(0, Math.sin(Math.PI * (0.03) * idle_time++));
    }

    /**
     * The updateType() method determines how many iterations have passed since last image change
     * and updates the Bird's image accordingly.
     */
    public void updateType(){
        // Only change image if Bird is alive.
        if(alive){
            type_time++; // Count the number of iterations that have passed since last change.

            // If iterations is equal to the delay time, change image.
            if(type_time == TYPE_DELAY){
                type++;
                type_time = 0;
            }
            
            // Reset image if last image is reached.
            if(type == 3){
                type = 0;
            }

            // Update image.
            super.updateImage("bird" + type);
        }
    }

    // Return alive value.
    public boolean isAlive(){
        return alive;
    }

    // Return active value.
    public void setListLess(){
        this.active = false;
    }

    /**
     * The setDead() method ensures the Bird's alive status is set to false,
     * it's facing the ground while falling and it falls almost instantly.
     */
    public void setDead(){
        this.alive = false;
        super.setRotation(90);
        super.setVelocity(0, DEAD_VELOCITY);
    }

    /**
     * The jump() method sets the current velocity to a negative value to ensure
     * it goes in the upwards direction. It also sets the rotation value to some value
     * to ensure its facing the sky while jumping. Lastly, it activates the active status and
     * resets the time since the last rotation variable (rotation_time).
     */
    public void jump(){
        if(alive){
            super.setVelocity(0, JUMP_VELOCITY);
            super.setRotation(JUMP_ROTATION);
            active = true;
            rotation_time = 0;
        }
    }

}
