package com.vizitkar.Buttons.QRCodeButton;
import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import javafx.embed.swing.SwingFXUtils;

import javafx.scene.control.Button;


import javafx.scene.image.Image;

import javafx.scene.web.WebView;

import com.vizitkar.UI.CardUI.CardUI;
import com.vizitkar.Utils.QRCodeUtilis.QRCodeUtils;


/**
 * This class creates a button for generating a QR Code containing the user's business card information.
 */
public class QRCodeButton {

    /**
     * This method creates a "Generate QR Code" button for the given user details and WebView.
     * Clicking the button generates a QR Code based on the user's vCard data and updates the WebView to display it.
     *
     * @param name User's name.
     * @param surname User's surname.
     * @param title User's title.
     * @param company User's company.
     * @param address User's address.
     * @param phone User's phone number.
     * @param email User's email address.
     * @param website User's website.
     * @param webView The WebView where the generated QR Code will be displayed.
     * @return A Button object with the label "Generate QR Code".
     */
    public static Button createQRCodeButton(String name, String surname, String title, String company, String address, String phone, String email, String website, WebView webView) {
        Button generateQRButton = new Button("Generate QR Code");

        // Set action handler for the button
        generateQRButton.setOnAction(event -> {
            // Print user info for debugging (commented out)
            // System.out.println("Generating QR for: " + name + ", " + surname + ", " + title + ", " + company + ", " + address + ", " + phone + ", " + email + ", " + website);

            // Generate vCard data string containing user information
            String vCardData = QRCodeUtils.generateVCardData(name, surname, title, company, address, phone, email, website);

            try {
                // Generate QR Code image from vCard data
                BitMatrix bitMatrix = new QRCodeWriter().encode(vCardData, BarcodeFormat.QR_CODE, 200, 200);
                BufferedImage qrCodeBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                Image qrCodeImage = SwingFXUtils.toFXImage(qrCodeBufferedImage, null);

                // Convert QR Code image to Base64 string
                String qrCodeBase64 = CardUI.convertImageToBase64(qrCodeImage);

                // Update WebView content to display the QR Code image
                webView.getEngine().executeScript(
                        "document.getElementById('qr-code').innerHTML = '<img src=\"data:image/png;base64," + qrCodeBase64 + "\" style=\"width:100px; height:100px; display:block;\" />'; " +
                                "document.getElementById('qr-code').style.display = 'block';"
                );
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });

        return generateQRButton;
    }
}

