package com.sid.onlonebanking.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sid.onlonebanking.service.GenerateStatementService;
import com.sid.onlonebanking.service.S3Service;
import com.sid.onlonebanking.vo.PrimaryTransaction;

@Service
public class GenerateStatementServiceImpl implements GenerateStatementService {
	
	@Autowired
	private S3Service s3Services;
	
	@Value("${jsa.s3.uploadfile}")
	private String uploadFilePath;
	
	@Value("${jsa.s3.downloadfile}")
	private String downloadFilePath;
	
	//String FILE = "/Users/sid/Documents/upload_1/user_"+new Date().toString()+".pdf";
	Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
	Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
	Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
	Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);	
	
	@Override
	public void createPdf(List<PrimaryTransaction> pList) {
	 	String userName = getUsernameFromList(pList);
	 	String fullFilePath = uploadFilePath+userName+"_"+new Date()+".pdf";
		 try {
	        	Document document = new Document();
	        	
	        	FileOutputStream fi = new FileOutputStream(fullFilePath);
	        	PdfWriter.getInstance(document, fi);
	        	 document.open();
	        	 addMetaData(document);
	        	addTitlePage(document);
	        	 createTable(document, pList);
	        	 document.close();
	        }catch (Exception e) {
				System.out.println(e);
			}
		 File file = new File(uploadFilePath+userName+"_"+new Date()+".pdf");
		 System.out.println(file.getAbsolutePath());
		 System.out.println(file.getName());
		 s3Services.uploadFile(file.getName(), fullFilePath,userName);
		
	}
	
	private void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }
	
	private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
	
	private void addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		 preface.add(new Paragraph("Fish Bank", catFont));
		 addEmptyLine(preface, 2);
		 preface.add(new Paragraph("Report generated by Fish Bank System "  + ", " + new Date(), smallBold));
		 addEmptyLine(preface, 3);
		 preface.add(new Paragraph("This document describes something which is very important", smallBold));
		 addEmptyLine(preface, 8);
		 preface.add(new Paragraph("This document is a preliminary version and subject to your license agreement", redFont));
		 document.add(preface);
		 document.newPage();
	}
	
	private void createTable(Document document, List<PrimaryTransaction> pList) throws ParseException, DocumentException {
		 PdfPTable table = new PdfPTable(new float[] { 8, 8, 8, 8,8,8 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell("Date");
        table.addCell("Amount");
        table.addCell("AvailableBalance");
        table.addCell("Type");
        table.addCell("Description");
        table.addCell("Status");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells(); 
        
        for (int j=0;j<cells.length;j++){
   	     cells[j].setBackgroundColor(BaseColor.GRAY);
        }
   	     for(PrimaryTransaction p : pList) {
   	    	 String date = toDate(p.getDate());
   	    	 table.addCell(date);
   	    	 table.addCell(p.getAmount());
   	    	 String avb = Double.toString(p.getAvailableBalance());
   	    	 table.addCell(avb);
   	    	 table.addCell(p.getType());
   	    	 table.addCell(p.getDescription());
   	    	 table.addCell(p.getStatus());
   	     }
        document.add(table);
	}
	
	private String toDate(Date appDate) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = (Date)formatter.parse(appDate.toString());
		System.out.println(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
		System.out.println("formatedDate : " + formatedDate);    
		return formatedDate;
	}
	
	private String getUsernameFromList(List<PrimaryTransaction> pList) {
		String userName = "";
		Set<PrimaryTransaction> set = new HashSet<>(pList);
		for(PrimaryTransaction p : set) {
			userName = p.getUsername();
		}
		return userName;
	}
	

}
