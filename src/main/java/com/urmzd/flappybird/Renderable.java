package com.urmzd.flappybird;

// IMPORTS
import javafx.scene.canvas.GraphicsContext;

/**
 * The Renderable interface contains methods used in the rendering of
 * a object onto a canvas.
 */
public interface Renderable {

    /**
     * The render() method draws an image onto the canvas at specified cordinates.
     * 
     * @param gc The Object which should draw the image onto the canvas.
     */
    public void render(GraphicsContext gc);
}
