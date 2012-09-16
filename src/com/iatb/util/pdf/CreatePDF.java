package com.iatb.util.pdf;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class CreatePDF {

	/**
	 * 
	 */
	public static void init(){
		try {
			Document document = new Document(PageSize.A4,10,10,5,5);  
			PdfWriter.getInstance(document, new FileOutputStream("C:/Documents and Settings/Administrator/My Documents/pdf/Helloworld.PDF"));  
			document.open();  
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
			Font FontChineseTitle = new Font(bfChinese, 16, Font.BOLD,Color.red); 
			Font FontChineseContent = new Font(bfChinese, 12, Font.NORMAL); 
			Font FontChineseEnd = new Font(bfChinese, 12, Font.BOLDITALIC); 
			Paragraph title  =   new  Paragraph( "李小龙截拳道核心理论" , FontChineseTitle);  
			title.setAlignment(Paragraph.ALIGN_CENTER);
			Paragraph content=new  Paragraph("\n  李小龙截拳道吸收有用的技术动作,加上自己的专长,从而增强实战能力," +
					"\n向学以致用的方向发展,探求和创造真正属自己的武技.\n" +
					"当你学会了截拳道以后,你所发出的一拳一脚不应当仍是属于李小龙的,而应当是真正属于你自己的,\n" +
					"并使你能有实战中发挥出最佳威力的任何门派的武功招式. \n\n\n\n",FontChineseContent);   
			content.setAlignment(Paragraph.ALIGN_LEFT);
			Paragraph end1=new  Paragraph("本文转载自:",FontChineseEnd);
			Chunk chunk1 =new Chunk("http://www.bruceleewhy.com/bruceleejeetkunedo/jeetkunedomind/bruceleejeetkunecoretheory.html",FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,Color.blue)).setAnchor("http://www.bruceleewhy.com/bruceleejeetkunedo/jeetkunedomind/bruceleejeetkunecoretheory.html");
			Paragraph end2=new  Paragraph("\n李小龙十万个为什么",FontChineseEnd);
			Chunk chunk2 =new Chunk("http://www.bruceleewhy.com",FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,Color.blue)).setAnchor("http://www.bruceleewhy.com");
			Paragraph end3=new  Paragraph("\n作者：BruceLeeWhy, 李小龙十万个为什么在线第一品牌",FontChineseEnd);
			document.add(title);
			document.add(content);
			document.add(end1);  
			document.add(chunk1);  
			document.add(end2);  
			document.add(chunk2);  
			document.add(end3);  
			document.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}  

	}
	public static void main(String[] args) {
		init();
	}
	
}
