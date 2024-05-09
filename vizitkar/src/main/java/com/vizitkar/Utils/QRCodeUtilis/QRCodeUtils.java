package com.vizitkar.Utils.QRCodeUtilis;




/**
 * This class provides utility methods for generating QR codes containing vCard data.
 */
public class QRCodeUtils {
    
    /**
     * Generates vCard data based on the provided information.
     * 
     * @param name     the name of the person
     * @param surname  the surname of the person
     * @param title    the title of the person
     * @param company  the company of the person
     * @param address  the address of the person
     * @param phone    the phone number of the person
     * @param email    the email address of the person
     * @param website  the website URL of the person
     * @return         the generated vCard data as a string
     */
    public static String generateVCardData(String name, String surname, String title, String company, String address, String phone, String email, String website) {
        return "BEGIN:VCARD\r\n" +
               "VERSION:3.0\r\n" +
               "FN:" + name + " " + surname + "\r\n" +
               "TITLE:" + title + "\r\n" +
               "ORG:" + company + "\r\n" +
               "TEL:" + phone + "\r\n" +
               "EMAIL:" + email + "\r\n" +
               "URL:" + website + "\r\n" +
               "ADR:" + address + "\r\n" +
               "END:VCARD";
    }
}
