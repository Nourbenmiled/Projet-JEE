package model;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.io.font.PdfFont;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public void createPdf(String dest, Etudiant etudiant, List<Inscription> inscriptions) throws IOException {
        // Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Load custom fonts (if any)
        PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");

        // Add title with larger font and centered alignment
        Paragraph title = new Paragraph("Informations de l'étudiant")
                            .setFont(font)
                            .setFontSize(18)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setMarginBottom(20);
        document.add(title);

        // Add student information with better formatting
        Paragraph studentInfo = new Paragraph()
            .add("Nom: " + etudiant.getNom() + "\n")
            .add("Prénom: " + etudiant.getPrenom() + "\n")
            .add("Contact: " + etudiant.getContact())
            .setFontSize(12)
            .setMarginBottom(20);
        document.add(studentInfo);

        // Add a table for courses and grades
        document.add(new Paragraph("Liste des cours et des notes")
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setMarginBottom(10));

        // Create a table with 3 columns
        Table table = new Table(3);
        table.setWidthPercent(100); // Ensure the table spans the full width of the page
        table.addCell(new Cell().add(new Paragraph("Nom du cours")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold().setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph("Notes")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold().setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph("Moyenne")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold().setTextAlignment(TextAlignment.CENTER));

        // Populate the table with data
        for (Inscription inscription : inscriptions) {
            table.addCell(new Cell().add(new Paragraph(inscription.getCours().getNom()))
                                .setTextAlignment(TextAlignment.LEFT));
            
            StringBuilder notes = new StringBuilder();
            double total = 0;
            int count = 0;
            for (Resultat note : inscription.getCours().getResultats()) {
                if (count > 0) {
                    notes.append(", ");
                }
                notes.append(note.getNote());
                total += note.getNote();
                count++;
            }
            double moyenne = (count == 0) ? 0 : total / count;
            
            table.addCell(new Cell().add(new Paragraph(notes.toString()))
                                .setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f", moyenne)))
                                .setTextAlignment(TextAlignment.CENTER));
        }

        // Add table to the document
        document.add(table);

        // Close the document
        document.close();
    }
}
