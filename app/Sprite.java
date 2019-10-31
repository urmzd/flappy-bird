package com.flappybird.app;

// IMPORTS.
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 * The Sprite class contains a blueprint for the construction of a sprite
 * for the game.
 * 
 * @author Urmzd Mukhammadnaim.
 */

public class Sprite implements Renderable, Updatable, Rotatable {

    // ATTRIBUTES.
    private double[] position;
    private double[] velocity;
    private double[] dimension;
    private double[] acceleration;
    
    private double width;
    private double height;
    private double rotation;

    private Image image;
    
    /**
     * The Sprite constructor defines a Sprite for use in the game.
     * 
     * @param position A double containing (x,y) coordinates.
     * @param imageName A string containing the file-name of an image.
     */
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

    /**
     * The Sprite constructor defines a Sprite for use in the game.
     * 
     * @param position A double containing (x,y) coordinates.
     * @param velocity A double containing the (x, y) velocities.
     * @param acceleration A double containing the (x,y) accelerations.
     * @param imageName A string containing the file-name of an image.
     */
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

    /**
     * The updateImage() method changes the current image of the sprite and its dimensions.
     * @param imageName A string containing the file-name of the new image.
     */
    public void updateImage(String imageName){
        image = new Image("/" + imageName + ".png");
        dimension[0] = image.getWidth();
        dimension[1] = image.getHeight();
    }

    /**
     * The update[vector] methods increments the current vector with the specified values.
     * @param x A double with the value to increment the x vector component with.
     * @param y A double with the value to increment the y vector component with.
     */
    public void updateAcceleration(double x, double y){
        acceleration[0] += x;
        acceleration[1] += y;
    }

    public void updateVelocity(){
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
    }

    public void updatePosition(){
        position[0] += velocity[0];
        position[1] += velocity[1];
    }

    
    public void updateRotation(double rotation){
        this.rotation += rotation;
    }

    // Accesors and mutators.
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

    public double[] getDimension(){
        return dimension;
    }

    /**
     * The stop() method stops the Sprite from moving by reducing the acceleration(s) and velocities to 0.
     */
    public void stop(){

        for(int i = 0; i < acceleration.length; i++){
            acceleration[i] = 0;
            velocity[i] = 0;
        }

    }

    /**
     * The getBoundingBox() creates a rectangle at the sprites current position with its current dimensions.
     * @return A rectangle with the dimensions of the Sprite.
     */
    public BoundingBox getBoundingBox(){
        return new BoundingBox(position[0], position[1], dimension[0], dimension[1]);
    }
    
    /**
     * The intersects() method determines if two Sprites intersect.
     * 
     * @return A true value if the Sprite's BoundingBox intersects with another Sprite's BoundingBox.
     */
    public boolean intersects(Sprite s){
        return this.getBoundingBox().intersects(s.getBoundingBox());
    }

    /**
     * The update() method updates the current velocity and current position of the Sprite.
     */
    @Override
    public void update() {
        updateVelocity();
        updatePosition();
    }

    /**
     * The render() method stores a copy of the Sprite's current transformations,
     * applies new transformations to it's image, draw's the image,
     * then restores the Sprite to its previous state.
     * 
     * @param gc The tool which will be used to draw the image onto the canvas.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.save();
        rotate(gc);
        gc.drawImage(image, position[0], position[1]);
        gc.restore();
    }

    /**
     * The rotate() method rotates an image from it's center point.
     * 
     * @param gc The tool which will be used to draw the image onto the canvas.
     */
    @Override
    public void rotate(GraphicsContext gc) {
        Rotate r = new Rotate(rotation, position[0] + dimension[0] / 2, position[1] + dimension[1] / 2);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

    }

}
