package com.vizitkar.Helper.ImageTransitionHelper;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This class provides a helper method to create an image transition animation for an ImageView.
 */
public class ImageTransitionHelper {

    /**
     * This method creates a continuous image transition animation for the given ImageView.
     * It cycles through a predefined set of images, displaying each one for a specific duration.
     *
     * @param imageView The ImageView where the image transition will be applied.
     */
    public static void createImageTransition(ImageView imageView) {
        // Define image paths
        Image[] images = {
                new Image("img/1.jpg"),
                new Image("img/2.jpg"),
                new Image("img/3.jpg")
        };

        // Create a timeline for the animation
        Timeline timeline = new Timeline();

        // Define keyframes for each image transition
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.imageProperty(), images[0])), // Set first image at 0 seconds
                new KeyFrame(Duration.seconds(3), new KeyValue(imageView.imageProperty(), images[1])), // Transition to second image at 3 seconds
                new KeyFrame(Duration.seconds(6), new KeyValue(imageView.imageProperty(), images[2]))  // Transition to third image at 6 seconds
        );

        // Set the timeline to repeat indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Play the animation
        timeline.play();
    }
}


