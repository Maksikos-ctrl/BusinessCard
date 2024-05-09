// package com.vizitkar.BusinessCardGUI;


// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;

// import javafx.scene.control.Slider;


// import javafx.scene.web.WebView;


// public class TextResizeButton {
//     private Button createResizeTextButton(WebView webView) {
//         Button resizeTextButton = new Button("Resize Text");
//         resizeTextButton.setOnAction(event -> {
//             Slider fontSizeSlider = new Slider(8, 24, 14);
//             fontSizeSlider.setShowTickLabels(true);
//             fontSizeSlider.setShowTickMarks(true);
//             fontSizeSlider.setMajorTickUnit(1);
//             fontSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> webView.getEngine().executeScript("document.body.style.fontSize='" + newVal.intValue() + "px';"));
//             Alert alert = new Alert(Alert.AlertType.INFORMATION);
//             alert.setTitle("Adjust Font Size");
//             alert.setHeaderText("Adjust the font size using the slider.");
//             alert.getDialogPane().setContent(fontSizeSlider);
//             alert.showAndWait();
//         });
//         return resizeTextButton;
//     }

    
// }


package com.vizitkar.Buttons.TextResizeButton;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.web.WebView;
import javafx.scene.control.Alert;

/**
 * This class creates a button that allows users to adjust the font size of the text in a WebView.
 */
public class TextResizeButton {

    /**
     * This method creates a "Resize Text" button for the given WebView.
     * Clicking the button displays a dialog with a slider to control the font size of the WebView content.
     *
     * @param webView The WebView whose text size can be adjusted.
     * @return A Button object with the label "Resize Text".
     */
    public static Button createResizeTextButton(WebView webView) {
        Button resizeTextButton = new Button("Resize Text");

        // Set action handler for the button
        resizeTextButton.setOnAction(event -> {
            // Create a slider for adjusting font size (8 to 24, starting at 14)
            Slider fontSizeSlider = new Slider(8, 24, 14);
            fontSizeSlider.setShowTickLabels(true); // Display tick labels for values
            fontSizeSlider.setShowTickMarks(true); // Display tick marks
            fontSizeSlider.setMajorTickUnit(1); // Major tick marks every 1 unit

            // Update WebView font size based on slider value change
            fontSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                webView.getEngine().executeScript("document.body.style.fontSize='" + newVal.intValue() + "px';");
            });

            // Create an information dialog with the slider
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adjust Font Size");
            alert.setHeaderText("Adjust the font size using the slider.");
            alert.getDialogPane().setContent(fontSizeSlider); // Add slider to dialog content

            alert.showAndWait(); // Display the dialog and wait for user interaction
        });

        return resizeTextButton;
    }
}

