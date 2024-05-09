package com.vizitkar.Buttons.ExportButton;





import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import com.vizitkar.Utils.FileIOUtils.FileIOUtils;

import javafx.embed.swing.SwingFXUtils;


import javafx.scene.SnapshotParameters;

import javafx.scene.control.Button;



import javafx.scene.image.WritableImage;


import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * This class creates a button for exporting the content of a WebView as an image or PDF.
 */
public class ExportButton {

    /**
     * This method creates an Export button for the given WebView and Stage.
     * Clicking the button allows users to save the WebView content as an image or PDF.
     *
     * @param webView The WebView containing the content to be exported.
     * @param designStage The Stage where the WebView is displayed.
     * @return A Button object with the label "Export".
     */
    public static Button createExportButton(WebView webView, Stage designStage) {
        Button exportButton = new Button("Export");

        // Set action handler for the button
        exportButton.setOnAction(event -> {
            // Capture a snapshot of the WebView content
            WritableImage snapshot = webView.snapshot(new SnapshotParameters(), null);

            // Open a file chooser dialog for saving the export
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image or PDF");

            // Define file extension filters for supported formats
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File file = fileChooser.showSaveDialog(designStage);
            if (file != null) {
                try {
                    // Save the captured image based on the chosen format
                    saveAsFile(snapshot, file);
                } catch (IOException e) {
                    System.err.println("Failed to save file: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        return exportButton;
    }

    /**
     * Saves the captured image to the specified file based on its extension.
     *
     * @param image The captured image from the WebView.
     * @param file The chosen file for saving the export.
     * @throws IOException If an error occurs during file saving.
     */
    private static void saveAsFile(WritableImage image, File file) throws IOException {
        String extension = getFileExtension(file);
        switch (extension) {
            case "pdf":
                // Use FileIOUtils.saveAsPDF (assumed to be in another class) to save as PDF
                FileIOUtils.saveAsPDF(image, file);
                break;
            case "png":
            case "jpg":
            case "jpeg":
                // Use ImageIO to write the image to the file
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), extension, file);
                System.out.println("Image saved successfully to: " + file.getAbsolutePath());
                break;
            default:
                System.out.println("Unsupported file format: " + extension);
        }
    }

    /**
     * Extracts the file extension from the given file name.
     *
     * @param file The file object containing the name.
     * @return The extracted file extension in lowercase (or empty string if not found).
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf('.') > 0) {
            return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        }
        return "";
    }
}    