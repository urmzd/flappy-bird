package com.flappybird.app;

import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Sprite implements Renderable, Updatable, Rotatable {

    private double[] position;
    private double[] velocity;
    private double[] dimension;
    private double[] acceleration;
    
    private double width;
    private double height;
    private double rotation;

    private Image image;
    
    public Sprite(double[] position, String imageName){
        this.position = position;
        this.rotation = 0;
        this.velocity = new double[] {0.0, 0.0};
        this.acceleration = new double[] {0.0, 0.0};
        this.image = new Image("/" + imageName + ".png");
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.dimension = new double[] {width, height};
    }

    public Sprite(double[] position, double[] velocity, double[] acceleration, String imageName){
        this.position = position;
        this.rotation = 0;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.image = new Image("/" + imageName + ".png");
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.dimension = new double[] {width, height};
    }

    public void updateImage(String imageName){
        image = new Image("/" + imageName + ".png");
        dimension[0] = image.getWidth();
        dimension[1] = image.getHeight();
    }

    public void updateAcceleration(double x, double y){
        acceleration[0] += x;
        acceleration[1] += y;
    }

    public void setAcceleration(double x, double y){
        acceleration[0] = x;
        acceleration[1] = y;
    }

    public double[] getAcceleration(){
        return acceleration;
    }

    public double[] getVelocity(){
        return velocity;
    }

    public void setVelocity(double x, double y){
        velocity[0] = x;
        velocity[1] = y;
    }

    public void updateVelocity(){
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
    }

    public void stop(){

        for(int i = 0; i < acceleration.length; i++){
            acceleration[i] = 0;
            velocity[i] = 0;
        }

    }

    public double[] getPosition(){
        return position;
    }

    public void setPosition(double x, double y){
        position[0] = x;
        position[1] = y;
    }

    public double getRotation(){
        return rotation;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    public void updateRotation(double rotation){
        this.rotation += rotation;
    }

    public void updatePosition(){
        position[0] += velocity[0];
        position[1] += velocity[1];
    }

    public BoundingBox getBoundingBox(){
        return new BoundingBox(position[0], position[1], dimension[0], dimension[1]);
    }
    
    public boolean intersects(Sprite s){
        return this.getBoundingBox().intersects(s.getBoundingBox());
    }

    public double[] getDimension(){
        return dimension;
    }

    @Override
    public void update() {
        updateVelocity();
        updatePosition();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.save();
        rotate(gc);
        gc.drawImage(image, position[0], position[1]);
        gc.restore();
    }

    @Override
    public void rotate(GraphicsContext gc) {
        Rotate r = new Rotate(rotation, position[0] + dimension[0] / 2, position[1] + dimension[1] / 2);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

    }

}