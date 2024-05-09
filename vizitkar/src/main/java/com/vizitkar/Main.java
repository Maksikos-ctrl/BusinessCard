package com.vizitkar;

import com.vizitkar.BusinessCardGUI.BusinessCardGUI.BusinessCardGUI;

import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      
        BusinessCardGUI.createMainWindow(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
