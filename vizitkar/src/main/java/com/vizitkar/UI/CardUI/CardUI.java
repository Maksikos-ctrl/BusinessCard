package com.vizitkar.UI.CardUI;


import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.util.Base64;


import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CardUI {
    public static String businessCardHTML(String name, String surname, String title, String company, String address,
            String phone, String email, String website, Image image, Color bgColor) {
        String base64Image = convertImageToBase64(image);
        return "<html><head><style>" +
        "body { font-family: 'Arial', sans-serif; margin: 0; padding: 0; }" +
        ".card { position: relative; max-width: 690px; width: 100%; height: auto; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.3); background-color: #fff; margin: 20px; }" +
        ".header { height: 150px; background: linear-gradient(to right, #ff0000, #d20000); color: white; display: flex; flex-direction: column; justify-content: center; padding: 20px; position: relative; }" +
        ".logo { position: absolute; right: 10px; top: 10px; width: 50px; height: 50px; border-radius: 50%; overflow: hidden; background: white; }" +
        ".logo img { max-width: 100%; height: auto; }" +
        ".contact-info { padding: 20px; }" +
        ".contact-row { display: flex; align-items: center; margin-bottom: 10px; }" +
        ".contact-icon { width: 24px; height: 24px; margin-right: 10px; background-size: cover; }" +
        ".qr-code { position: absolute; bottom: 20px; right: 20px; }" +
        ".overlay { position: absolute; top: 10px; left: 10px; right: 10px; bottom: 10px; background: rgba(255, 255, 255, 0.7); }" +
        ".company-footer { background: #d20000; color: white; text-align: center; padding: 20px; font-size: 14px; }" +
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
        "   <div class='overlay'></div>" + // Overlay
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

    /**
        * Converts an Image object to a Base64 encoded string representation.
        *
        * @param image the Image object to be converted
        * @return the Base64 encoded string representation of the image
        */
    public static String convertImageToBase64(Image image) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imageData = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(imageData);
    }
}
   


   

    

