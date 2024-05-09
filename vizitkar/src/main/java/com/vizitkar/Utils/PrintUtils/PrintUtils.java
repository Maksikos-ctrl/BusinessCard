package com.vizitkar.Utils.PrintUtils;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;


/**
 * This class provides utility methods for printing functionality.
 */
public class PrintUtils {

    /**
     * Prints the specified node using a printer job.
     * 
     * @param node   the node to be printed
     * @param owner  the owner window for the print dialog
     */
    public static void printNode(Node node, Window owner) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(owner)) {
            boolean success = printerJob.printPage(node);
            if (success) {
                printerJob.endJob();
            } else {
                System.out.println("Printing failed.");
            }
        } else {
            System.out.println("Could not create a printer job.");
        }
    }

    /**
     * Checks for available print services and prints their names.
     */
    public static void checkPrintServices() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        if (printServices.length > 0) {
            for (PrintService printer : printServices) {
                System.out.println("Printer: " + printer.getName());
            }
        } else {
            System.out.println("No printers found.");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Printer Check");
            alert.setHeaderText(null);
            alert.setContentText("No printers found on this system.");
            alert.showAndWait();
        }
    }
}
