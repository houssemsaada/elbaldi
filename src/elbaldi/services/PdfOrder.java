/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import elbaldi.models.commande;
import java.io.FileOutputStream;
import javafx.scene.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author houss
 */
public class PdfOrder {

    public void orderPdf(commande c) {
        try {

            Document document = new Document();
          //  PdfWriter.getInstance(document, new FileOutputStream("C://ordrePdfn" + c.getId_cmd() + ".pdf"));
                        PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\images\\ordrePdfn" + c.getId_cmd() + ".pdf"));

            
            document.open();
            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            com.itextpdf.text.Font fontgras = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
                        com.itextpdf.text.Font fontgrastitre = FontFactory.getFont(FontFactory.TIMES_BOLD,28, BaseColor.BLACK);
 document.add(new Paragraph("\n"));
            Chunk chunk = new Chunk("                              Facture ", fontgrastitre);
            
            document.add(chunk);
            document.add(new Paragraph("\n\n\n"));
            PdfPTable pdfPTable = new PdfPTable(1);
            
            PdfPCell pdfPCell1 = new PdfPCell();
            pdfPCell1.setBorderColor(BaseColor.BLACK);
            pdfPCell1.setBorderWidth(2);
            Image image = Image.getInstance("http://localhost/images/Al%20Baldi.png");
            image.scaleToFit(200, 200);
            image.setAbsolutePosition(190, 520);
            
           document.add(image);
            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
            
            pdfPCell1.addElement(new Paragraph("\n\n"));
            chunk = new Chunk("    Client : ",fontgras);
            pdfPCell1.addElement(chunk);
            chunk = new Chunk( "    "+c.getPan().getU1().getPrenom() +" "+ c.getPan().getU1().getNom(), font);
            pdfPCell1.addElement(chunk);
            pdfPCell1.addElement(new Paragraph("\n"));
            chunk = new Chunk("    adresse : " , fontgras);
            pdfPCell1.addElement(chunk);
            chunk = new Chunk( "    "+c.getAdresse(), font);
            pdfPCell1.addElement(chunk);
            pdfPCell1.addElement(new Paragraph("\n"));
            chunk = new Chunk("    telephone :" , fontgras);
            pdfPCell1.addElement(chunk);
            chunk = new Chunk( "    "+c.getPan().getU1().getNumTel()+"", font);
            pdfPCell1.addElement(chunk);
            pdfPCell1.addElement(new Paragraph("\n"));
            chunk = new Chunk("    total  :" , fontgras);
            pdfPCell1.addElement(chunk);
            chunk = new Chunk( "    "+c.getTotal()+" TND", font);
            pdfPCell1.addElement(chunk);
            pdfPCell1.addElement(new Paragraph("\n\n"));
             pdfPTable.addCell(pdfPCell1);
             document.add(pdfPTable);

            document.close();
            System.out.println("pdf ajouter !");
        } catch (Exception e) {
            System.out.println("Error PDF " + e);

        }

    }
}
