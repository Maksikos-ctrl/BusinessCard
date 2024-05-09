package com.vizitkar.BusinessCardGUI.Dialogs;



import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;  

import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.vizitkar.Buttons.ExportButton.ExportButton;
import com.vizitkar.Buttons.QRCodeButton.QRCodeButton;
import com.vizitkar.Buttons.TextResizeButton.TextResizeButton;
import com.vizitkar.Manager.TemplateManager.TemplateManager;
import com.vizitkar.UI.CardUI.CardUI;
import com.vizitkar.Utils.PrintUtils.PrintUtils;


/**
 * The Dialogs class provides methods for handling dialog windows in the business card GUI application.
 */
public class Dialogs {

    public static void handleAddButton(Stage primaryStage) {
        Stage inputStage = new Stage();
        inputStage.setTitle("Enter Business Card Information");
    
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
    
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField surnameField = new TextField();
        surnameField.setPromptText("Surname");
    
        ComboBox<String> titleComboBox = new ComboBox<>();
        titleComboBox.getItems().addAll("Chief", "Assistant", "Project Manager", "HR Manager", "Marketing Manager");
        titleComboBox.setPromptText("Title");
    
        TextField companyField = new TextField();
        companyField.setPromptText("Company");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
    
         
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField websiteField = new TextField();
        websiteField.setPromptText("Website");
    
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Surname:"), 0, 1);
        gridPane.add(surnameField, 1, 1);
        gridPane.add(new Label("Title:"), 0, 2);
        gridPane.add(titleComboBox, 1, 2);
        gridPane.add(new Label("Company:"), 0, 3);
        gridPane.add(companyField, 1, 3);
        gridPane.add(new Label("Address:"), 0, 4);
        gridPane.add(addressField, 1, 4);
        gridPane.add(new Label("Phone Number:"), 0, 5);
        gridPane.add(phoneField, 1, 5);
        gridPane.add(new Label("Email:"), 0, 6);
        gridPane.add(emailField, 1, 6);
        gridPane.add(new Label("Website:"), 0, 7);
        gridPane.add(websiteField, 1, 7);
    
      
        

    
        ImageView logoView = new ImageView();
        logoView.setFitWidth(100);
        logoView.setFitHeight(100);
        Button uploadLogoButton = new Button("Upload Logo");
        uploadLogoButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Logo Image");
            File selectedFile = fileChooser.showOpenDialog(inputStage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                logoView.setImage(image);
            }
        });
        gridPane.add(new Label("Logo:"), 0, 8);
        gridPane.add(uploadLogoButton, 1, 8);
        gridPane.add(logoView, 2, 8);
    
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> {
            // Validate input fields
            if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                    titleComboBox.getValue() == null || companyField.getText().isEmpty() ||
                    addressField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                    emailField.getText().isEmpty() || websiteField.getText().isEmpty()) {
                // Show warning if any input field is empty
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
                return; // Exit if any field is empty
            }
    
            // Phone validation: allow only numbers and "+"
            String phone = phoneField.getText();
            if (!phone.matches("[0-9+]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid phone number.");
                alert.showAndWait();
                return; // Exit if phone format is invalid
            }
    
            // Email validation: require "@" character
            String email = emailField.getText();
            if (!email.contains("@")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid email address.");
                alert.showAndWait();
                return; // Exit if email format is invalid
            }
    
            // Continue with processing if all validations pass
            VBox userDataBox = new VBox();
            userDataBox.getChildren().addAll(new Label("Name: " + nameField.getText()),
                    new Label("Surname: " + surnameField.getText()),
                    new Label("Title: " + titleComboBox.getValue()),
                    new Label("Company: " + companyField.getText()),
                    new Label("Address: " + addressField.getText()),
                    new Label("Phone: " + phoneField.getText()),
                    new Label("Email: " + emailField.getText()),
                    new Label("Website: " + websiteField.getText()));
    
            Stage userDataStage = new Stage();
            userDataStage.setTitle("User Data");
            userDataStage.setScene(new Scene(userDataBox));
            userDataStage.show();
    
            suggestDesignOptions(nameField.getText(), surnameField.getText(), titleComboBox.getValue(), companyField.getText(),
                    addressField.getText(), phoneField.getText(), emailField.getText(), websiteField.getText(),
                    logoView.getImage(), Color.WHITE);
    
            inputStage.close();
        });

        
    
        gridPane.add(continueButton, 0, 9, 3, 1);
    
        Scene inputScene = new Scene(gridPane);
        inputStage.setScene(inputScene);
    
        inputStage.show();
    }

    public static void suggestDesignOptions(String name, String surname, String title, String company, String address,
                                  String phone, String email, String website, Image image, Color bgColor) {
        Stage designStage = new Stage();
        designStage.setTitle("Design Options");

        WebView webView = setupWebView(name, surname, title, company, address, phone, email, website, image, bgColor);
        VBox designBox = new VBox(10);
        designBox.getChildren().add(webView);

        HBox buttonBox = setupButtonBox(webView, designStage, name, surname, title, company, address, phone, email, website, image);
        designBox.getChildren().add(buttonBox);

        Scene scene = new Scene(designBox, 800, 600);
        designStage.setScene(scene);
        designStage.show();
    }

    public static WebView setupWebView(String name, String surname, String title, String company, String address,
                                String phone, String email, String website, Image image, Color bgColor) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(CardUI.businessCardHTML(name, surname, title, company, address, phone, email, website, image, bgColor));
        return webView;
    }

    public static HBox setupButtonBox(WebView webView, Stage designStage, String name, String surname, String title, String company, String address, String phone, String email, String website, Image image) {
        Button exportButton = ExportButton.createExportButton(webView, designStage);
        Button generateQRButton = QRCodeButton.createQRCodeButton(name, surname, title, company, address, phone, email, website, webView);
        Button resizeTextButton = TextResizeButton.createResizeTextButton(webView);
        Button templateButton = new Button("Choose Template");
        templateButton.setOnAction(event -> TemplateManager.openTemplateChooser(designStage, webView, name, surname, title, company, address, phone, email, website, image));
        Button printButton = new Button("Print Business Card");
        printButton.setOnAction(e -> PrintUtils.printNode(webView, designStage));
        Button checkPrintersButton = new Button("Check Printers");
        checkPrintersButton.setOnAction(e -> PrintUtils.checkPrintServices());
    
        HBox buttonBox = new HBox(10, exportButton, generateQRButton, resizeTextButton, templateButton, printButton, checkPrintersButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }
}