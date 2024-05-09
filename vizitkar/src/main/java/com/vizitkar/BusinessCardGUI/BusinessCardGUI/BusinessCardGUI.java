package com.vizitkar.BusinessCardGUI.BusinessCardGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.vizitkar.BusinessCardGUI.Dialogs.Dialogs;
import com.vizitkar.UI.UIComponents.UIComponents;
import com.vizitkar.Utils.AnimationUtils.AnimationUtils;

/**
 * The BusinessCardGUI class represents the graphical user interface for the Vizitkar application.
 * It extends the Application class and provides the start method to initialize and display the GUI.
 */

public class BusinessCardGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vizitkar");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;");  // Set the root background to transparent

        

        Scene scene = new Scene(root, 800, 600, Color.TRANSPARENT);  // Set scene's initial fill to transparent

        // Set the top and center of the BorderPane using your UIComponents
        root.setTop(UIComponents.createLogoView());

        // Create and add the styled button at the center of the root pane
        StackPane buttonStackPane = UIComponents.createCenteredButton("+", e -> Dialogs.handleAddButton(primaryStage), 100, 100);
        root.setCenter(buttonStackPane);

        // Set up the animated background for the scene
        AnimationUtils.createdAnimatedBackground(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void createMainWindow(Stage primaryStage) {
        BusinessCardGUI businessCardGUI = new BusinessCardGUI();
        businessCardGUI.start(primaryStage);
    }

}



