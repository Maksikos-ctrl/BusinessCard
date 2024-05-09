package com.vizitkar.UI.UIComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;

/**
 * This class provides utility methods for creating common UI components with a consistent style.
 */
public class UIComponents {

    /**
     * This method creates a styled button with a green background, white text, and a specific font size.
     *
     * @param text The text to be displayed on the button.
     * @param action The event handler to be associated with the button click.
     * @return A Button object with the specified style and event handler.
     */
    public static Button createStyledButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        // Set button style using CSS syntax
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 24px;");
        button.setOnAction(action);
        return button;
    }

    /**
     * This method creates an ImageView for a logo with a predefined URL and sets its dimensions.
     *
     * @return An ImageView object displaying the logo image.
     */
    public static ImageView createLogoView() {
        ImageView logoView = new ImageView(new Image("https://logos-world.net/wp-content/uploads/2021/03/Kia-Symbol.png"));
        logoView.setFitWidth(100);
        logoView.setFitHeight(100);
        return logoView;
    }

    /**
     * This method creates a StackPane containing a styled button centered within the pane.
     *
     * @param text The text to be displayed on the button.
     * @param action The event handler to be associated with the button click.
     * @param width The desired width for the button and StackPane.
     * @param height The desired height for the button and StackPane.
     * @return A StackPane object containing the centered styled button.
     */
    public static StackPane createCenteredButton(String text, EventHandler<ActionEvent> action, double width, double height) {
        Button addButton = createStyledButton(text, action);
        addButton.setPrefSize(width, height);
        StackPane stack = new StackPane();
        stack.getChildren().add(addButton);
        StackPane.setAlignment(addButton, Pos.CENTER); // Minor correction: using setAlignment instead of a separate method call
        return stack;
    }
}

