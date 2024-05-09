package com.vizitkar.Manager.TemplateManager;
import java.util.Arrays;
import java.util.List;

import com.vizitkar.UI.CardUI.CardUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;  
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class TemplateManager {

    /**
     * This method displays a dialog for the user to choose a template based on their job title.
     *
     * @param parentStage The parent stage of the dialog.
     * @param webView The WebView where the business card design will be displayed.
     * @param name User's name.
     * @param surname User's surname.
     * @param title User's title.
     * @param company User's company.
     * @param address User's address.
     * @param phone User's phone number.
     * @param email User's email address.
     * @param website User's website (not used in this method).
     * @param image An optional image for the business card (can be null).
     */
    public static void openTemplateChooser(Stage parentStage, WebView webView, String name, String surname, String title, String company, String address, String phone, String email, String website, Image image) {
        // Create a new stage for the template selection dialog
        Stage stage = new Stage();
        stage.setTitle("Choose a Template");

        // Define layout for the dialog
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // Prepare alert for unauthorized access
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("You are not authorized to select this template.");

        // Define buttons for different templates
        Button template1 = new Button("Chief");
        template1.setUserData(1);
        Button template2 = new Button("Assistant");
        template2.setUserData(2);
        Button template3 = new Button("Project Manager");
        template3.setUserData(3);
        Button template4 = new Button("HR Manager");
        template4.setUserData(4);
        Button template5 = new Button("Marketing Manager");
        template5.setUserData(5);

        List<Button> buttons = Arrays.asList(template1, template2, template3, template4, template5);

        // Set button visibility and enabled state based on the title
        buttons.forEach(button -> {
            button.setDisable(!button.getText().equals(title));
            button.setVisible(button.getText().equals(title));
        });

        // Event handler for template buttons
        EventHandler<ActionEvent> templateHandler = event -> {
            Button source = (Button) event.getSource();
            if (source.isDisabled()) {
                alert.showAndWait();
            } else {
                int templateNumber = (int) source.getUserData();
                applyTemplate(templateNumber, webView, name, surname, title, company, address, phone, email, website, image);
            }
        };

        // Assign the same handler to all buttons
        buttons.forEach(button -> button.setOnAction(templateHandler));

        // Add UI elements to the layout
        layout.getChildren().addAll(new Label("Select a Template:"), template1, template2, template3, template4, template5);

        // Create the scene and set it on the stage
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);

        // Set the parent stage and show the dialog
        stage.initOwner(parentStage);
        stage.show();
    }

    /**
     * This method applies the chosen template to the WebView.
     *
     * @param templateNumber An integer representing the chosen template.
     * @param webView The WebView where the business card design will be displayed.
     * @param name User's name.
     * @param surname User's surname.
     * @param title User's title.
     * @param company User's company.
     * @param address User's address.
     * @param phone User's phone number.
     * @param email User's email address.
     * @param website User's website (not used in this method).
     * @param image An optional image for the business card (can be null).
     */
    
    
    
    
    
    /**
     * Applies a template to a WebView based on the given template number and input data.
     *
     * @param templateNumber The number of the template to apply.
     * @param webView The WebView to apply the template to.
     * @param name The name to be included in the template.
     * @param surname The surname to be included in the template.
     * @param title The title to be included in the template.
     * @param company The company to be included in the template.
     * @param address The address to be included in the template.
     * @param phone The phone number to be included in the template.
     * @param email The email address to be included in the template.
     * @param website The website URL to be included in the template.
     * @param image The image to be included in the template.
     */
    private static void applyTemplate(int templateNumber, WebView webView, String name, String surname, String title, String company, String address, String phone, String email, String website, Image image) {
        switch (templateNumber) {
            case 1:
                webView.getEngine().loadContent(generateTemplateHTML(0, name, surname, title, company, address, phone, email, website, image));
                break;
            case 2:
                webView.getEngine().loadContent(generateTemplateHTML(1, name, surname, title, company, address, phone, email, website, image));
                break;
            case 3:
                webView.getEngine().loadContent(generateTemplateHTML(2, name, surname, title, company, address, phone, email, website, image));
                break;
            case 4:
                webView.getEngine().loadContent(generateTemplateHTML(3, name, surname, title, company, address, phone, email, website, image));
                break;
            case 5:
                webView.getEngine().loadContent(generateTemplateHTML(4, name, surname, title, company, address, phone, email, website, image));
                break;    
        }
    }

    private static String generateTemplateHTML(int templateNumber, String name, String surname, String title, String company, String address, String phone, String email, String website, Image image) {
        String base64Image = (image != null) ? CardUI.convertImageToBase64(image) : "";
        String[] backgroundStyles = {
            "linear-gradient(135deg, #6a11cb 0%, #2575fc 100%)", // Template 1: Purple gradient
            "linear-gradient(45deg, #5433FF 0%, #20BDFF 50%, #A5FECB 100%)", // Template 1: Alternative gradient
            "linear-gradient(135deg, #11998e 0%, #38ef7d 100%)", // Template 2: Green gradient
            "linear-gradient(45deg, #0FF0B3 0%, #036ED9 100%)", // Template 2: Alternative gradient
            "linear-gradient(135deg, #ff0084 0%, #33001b 100%)", // Template 3: Red gradient
            "linear-gradient(45deg, #FAD961 0%, #F76B1C 100%)", // Template 3: Alternative gradient
            "linear-gradient(135deg, #fc5c7d 0%, #6a82fb 100%)", // Template 4: Pink to blue gradient
            "linear-gradient(45deg, #F7971E 0%, #FFD200 100%)", // Template 4: Alternative gradient
            "linear-gradient(135deg, #20bf55 0%, #01baef 100%)", // Template 5: Teal gradient
            "linear-gradient(45deg, #9D50BB 0%, #6E48AA 100%)" // Template 5: Alternative gradient
        };
        
    
        String backgroundStyle = backgroundStyles[templateNumber % backgroundStyles.length];
    
        return "<html><head><style>" +
                "body { font-family: 'Arial', sans-serif; margin: 0; padding: 0; }" +
                ".card { position: relative; max-width: 690px; width: 100%; height: auto; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.3); background-color: #fff; margin: 20px; border-radius: 10px; background: " + backgroundStyle + ";}" +
                ".header { height: 150px; color: white; display: flex; flex-direction: column; justify-content: center; padding: 20px; position: relative; }" +
                ".logo { position: absolute; right: 10px; top: 10px; width: 50px; height: 50px; border-radius: 50%; overflow: hidden; background: white; }" +
                ".logo img { max-width: 100%; height: auto; }" +
                ".contact-info { padding: 20px; }" +
                ".contact-row { display: flex; align-items: center; margin-bottom: 10px; }" +
                ".contact-icon { width: 24px; height: 24px; margin-right: 10px; background-size: cover; }" +
                ".qr-code { position: absolute; bottom: 20px; right: 20px; }" +
                ".overlay { position: absolute; top: 10px; left: 10px; right: 10px; bottom: 10px; background: rgba(255, 255, 255, 0.7); }" +
                ".company-footer { color: white; text-align: center; padding: 20px; font-size: 14px; }" +
                ".company-logo { width: 50px; height: 50px; margin: 0 auto; display: block; }" +
                "</style></head><body>" +
                "<div class='card'>" +
                "   <div class='header' id='header'>" +
                "       <div class='logo'><img src='data:image/png;base64," + base64Image + "'/></div>" +
                "       <strong>" + name + " " + surname + "</strong><br>" +
                "       " + title +
                "   </div>" +
                "   <div class='contact-info'>" +
                "       <div class='contact-row'><img src='https://w7.pngwing.com/pngs/731/937/png-transparent-mobile-phones-telephone-handset-phone-icon-miscellaneous-angle-telephone-call-thumbnail.png' class='contact-icon'>" + phone + "</div>" +
                "       <div class='contact-row'><img src='https://w7.pngwing.com/pngs/331/955/png-transparent-email-marketing-computer-icons-email-address-email-miscellaneous-text-rectangle-thumbnail.png' class='contact-icon'>" + email + "</div>" +
                "       <div class='contact-row'><img src='https://w7.pngwing.com/pngs/239/958/png-transparent-address-others-cdr-angle-black-thumbnail.png' class='contact-icon'>" + address + "</div>" +
                "   </div>" +
                "   <div class='qr-code' id='qr-code'></div>" +
                "   <div class='company-footer' id='footer'>" +
                "       <img src='https://logos-world.net/wp-content/uploads/2021/03/Kia-Symbol.png' class='company-logo'><br>" +
                "       " + company +
                "   </div>" +
                "   <div class='overlay'></div>" + 
                "</div>" +
                "<input type='color' id='colorPicker'>" +
                "<script>" +
                "   document.getElementById('colorPicker').addEventListener('input', function() {" +
                "       var color = this.value;" +
                "       document.getElementById('header').style.background = color;" +
                "       document.getElementById('footer').style.background = color;" +
                "       updateColors(color, ['header', 'footer']);" +
                "   });" +
                "   function updateColors(color, elementIds) {" +
                "       var rgb = color.replace('#', '').match(/.{1,2}/g).map(function(c) { return parseInt(c, 16); });" +
                "       var brightness = (rgb[0] * 299 + rgb[1] * 587 + rgb[2] * 114) / 1000;" +
                "       var textColor = brightness > 125 ? 'black' : 'white';" +
                "       elementIds.forEach(function(id) {" +
                "           document.getElementById(id).style.color = textColor;" +
                "       });" +
                "   }" +
                "</script>" +
                "</body></html>";
    }
}
