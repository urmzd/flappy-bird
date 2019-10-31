package com.flappybird.app;

public class Bird extends Sprite{

    private final double MAX_ACCELERATION = 0.1;
    private final double JUMP_VELOCITY = -2.5;
    private final double JUMP_ROTATION = -25;
    private final double ROTATION_DELAY = 10;
    private final double DEAD_VELOCITY = 6;
    private final int TYPE_DELAY = 10;

    private int type;

    private double idle_time;
    private double type_time;
    private double rotation_time; 
    private boolean alive;
    private boolean active;

    public Bird(double[] position, double[] velocity, double[] acceleration) {
        super(position, velocity, acceleration, "bird0");
        alive = true;
        active = false;
        type_time = 0;
        idle_time = 0;
        rotation_time = 0;
    }

    @Override
    public void update(){

        if(alive && !active){
            idle();
        }

        if(alive && active){
            double yAcceleration = super.getAcceleration()[1];

            rotation_time++;
    
            if(yAcceleration <= MAX_ACCELERATION){
                super.updateAcceleration(0, 0.006);
            }
    
            if(rotation_time >= ROTATION_DELAY){
                if(super.getRotation() <= 90){
                    super.updateRotation(yAcceleration * 17);
                }
            }
        }
        
        updateType();
        super.updateVelocity();
        super.updatePosition();
    }

    public void idle(){
        super.setVelocity(0, Math.sin(Math.PI * (0.03) * idle_time++));
    }

    public void updateType(){
        if(alive){
            type_time++;

            if(type_time == TYPE_DELAY){
                type++;
                type_time = 0;
            }
    
            if(type == 3){
                type = 0;
            }

            super.updateImage("bird" + type);
        }
    }

    public boolean isAlive(){
        return alive;
    }

    public void setListLess(){
        this.active = false;
    }

    public void setDead(){
        this.alive = false;
        super.setRotation(90);
        super.setVelocity(0, DEAD_VELOCITY);
    }

    public void jump(){
        if(alive){
            super.setVelocity(0, JUMP_VELOCITY);
            super.setRotation(JUMP_ROTATION);
            active = true;
            rotation_time = 0;
        }
    }

}