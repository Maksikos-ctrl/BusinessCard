package com.vizitkar.Utils.AnimationUtils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class provides utility methods for creating animations in JavaFX.
 */
public class AnimationUtils {

    /**
     * Creates an animated background for the given scene.
     *
     * @param scene the scene to apply the animated background to
     * @return the timeline representing the animated background
     */
    public static Timeline createdAnimatedBackground(Scene scene) {
        Color[] colors = {Color.rgb(10, 10, 100), Color.rgb(10, 100, 10), Color.rgb(100, 10, 10)};
        Timeline timeline = new Timeline();
        for (int i = 0; i < colors.length; i++) {
            KeyValue keyValue = new KeyValue(scene.fillProperty(), colors[i]);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 2), keyValue);
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
        return timeline;
    }
}
