package com.project.pantry.service.impl;


import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.project.pantry.model.Products;
 
 
public class PDFService {
    private List<Products> listUsers;
     
    public PDFService(List<Products> listUsers) {
        this.listUsers = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Product Id", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Product Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Product Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Category ID", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
        for (Products user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getName());
            table.addCell(user.getAdded_on());
            table.addCell(String.valueOf(user.getQty()));
            table.addCell(String.valueOf(user.getCategory_id()));
            
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Products", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
