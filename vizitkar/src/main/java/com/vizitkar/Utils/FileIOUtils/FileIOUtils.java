package com.vizitkar.Utils.FileIOUtils;

import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * This class provides utility methods for saving images and WritableImages to files.
 */
public class FileIOUtils {

    /**
     * This method saves a WritableImage to a file in the specified format.
     *
     * @param image The WritableImage to be saved.
     * @param file The file to save the image to.
     * @param formatName The desired format of the saved image (e.g., "png", "jpg").
     * @throws IOException If an error occurs during the saving process.
     */
    public static void saveImage(WritableImage image, File file, String formatName) throws IOException {
        // Convert WritableImage to a standard BufferedImage (Java AWT)
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

        // Use Java Image I/O library to write the BufferedImage to a file
        ImageIO.write(bImage, formatName, file);
    }

    /**
     * This method saves a WritableImage to a PDF file.
     *
     * @param image The WritableImage to be saved.
     * @param file The file to save the PDF to.
     */
    public static void saveAsPDF(WritableImage image, File file) {
        try {
            // Convert WritableImage to a BufferedImage
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            // Create a byte array to hold the image data in PNG format
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "PNG", baos);
            byte[] imageBytes = baos.toByteArray();

            // Create a new PDF document
            try (PDDocument doc = new PDDocument()) {
                // Create a new page in A4 size
                PDPage page = new PDPage(PDRectangle.A4);
                doc.addPage(page);

                // Convert image data to a PDF image object
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, imageBytes, "snapshot");

                // Calculate scaling to fit the image on the page while maintaining aspect ratio
                float scale = Math.min(page.getMediaBox().getWidth() / pdImage.getWidth(),
                                       page.getMediaBox().getHeight() / pdImage.getHeight());
                float scaledWidth = pdImage.getWidth() * scale;
                float scaledHeight = pdImage.getHeight() * scale;

                // Calculate positions to center the image on the page
                float xPosition = (page.getMediaBox().getWidth() - scaledWidth) / 2;
                float yPosition = (page.getMediaBox().getHeight() - scaledHeight) / 2;

                // Create a content stream for the page
                try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                    // Draw the image on the page at the calculated position and size
                    contents.drawImage(pdImage, xPosition, yPosition, scaledWidth, scaledHeight);
                }

                // Save the PDF document to the specified file
                doc.save(file);
            }
        } catch (IOException e) {
            // Handle potential exceptions during PDF creation
            System.err.println("An error occurred while saving the PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method gets the file extension from a File object.
     *
     * @param file The File object to get the extension from.
     * @return The file extension as a lowercase string (e.g., "png", "jpg"), or an empty string if no extension is found.
     */
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf('.') > 0) {
            // Extract the substring starting from the last dot (.) to get the extension
            return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        }
        return "";
    }
}
