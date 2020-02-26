/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import Entitie.Incident;
import Test.DataSource;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author AMINE
 */
public class PDFutil {
    
    Connection cn2;
    Statement ste;

    public PDFutil() {
        cn2 = DataSource.getInstance().getcnx();
    }
    
    
    
    Document doc = new Document();
        
    public void listActivite() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {  
        ste=cn2.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * from incident");
        PdfWriter.getInstance(doc, new FileOutputStream("./incident.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste incident:  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("id_inc",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("nom",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("description",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("createdby",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);
        
          cell = new PdfPCell(new Phrase("date_inc",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);
        
       
        
        while (rs.next()) {                
            
                Incident a = new Incident();
                a.setId_inc(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setCreatedby(rs.getInt(4));
               // a.setDate_inc(rs.getDate(5));
                
               /*DateFormat df = new SimpleDateFormat("hh:mm:ss");
               String rec = df.format(a.getId());
                String rank = Integer.toString(a.getId());*/
         
               
               
               
               
               cell = new PdfPCell(new Phrase(String.valueOf(a.getId_inc()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.cyan);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getNom(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.orange);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(a.getDescription(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.orange);
               table.addCell(cell);
        
        
               cell = new PdfPCell(new Phrase(String.valueOf(a.getCreatedby()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.orange);
               table.addCell(cell);
               
               /*cell = new PdfPCell(new Phrase(a.getDate_inc(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.orange);
               table.addCell(cell);*/
               
               
        }
        
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./incident.pdf"));
            }
}
