package com.flappybird.app;

// IMPORTS.
import javafx.scene.canvas.GraphicsContext;

/**
 * The Rotatable interface contains methods used in the rotation of an
 * image.
 * 
 * @author Urmzd Mukhammadnaim.
 */
public interface Rotatable {

    /**
     * The rotate() method defines the transformation used to rotate an image.
     * 
     * @param gc The Object which should draw the image onto the canvas.
     */
    public void rotate(GraphicsContext gc);
}
