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
			Paragraph title  =   new  Paragraph( "��С����ȭ����������" , FontChineseTitle);  
			title.setAlignment(Paragraph.ALIGN_CENTER);
			Paragraph content=new  Paragraph("\n  ��С����ȭ���������õļ�������,�����Լ���ר��,�Ӷ���ǿʵս����," +
					"\n��ѧ�����õķ���չ,̽��ʹ����������Լ����似.\n" +
					"����ѧ���˽�ȭ���Ժ�,����������һȭһ�Ų�Ӧ������������С����,��Ӧ���������������Լ���,\n" +
					"��ʹ������ʵս�з��ӳ�����������κ����ɵ��书��ʽ. \n\n\n\n",FontChineseContent);   
			content.setAlignment(Paragraph.ALIGN_LEFT);
			Paragraph end1=new  Paragraph("����ת����:",FontChineseEnd);
			Chunk chunk1 =new Chunk("http://www.bruceleewhy.com/bruceleejeetkunedo/jeetkunedomind/bruceleejeetkunecoretheory.html",FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,Color.blue)).setAnchor("http://www.bruceleewhy.com/bruceleejeetkunedo/jeetkunedomind/bruceleejeetkunecoretheory.html");
			Paragraph end2=new  Paragraph("\n��С��ʮ���Ϊʲô",FontChineseEnd);
			Chunk chunk2 =new Chunk("http://www.bruceleewhy.com",FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,Color.blue)).setAnchor("http://www.bruceleewhy.com");
			Paragraph end3=new  Paragraph("\n���ߣ�BruceLeeWhy, ��С��ʮ���Ϊʲô���ߵ�һƷ��",FontChineseEnd);
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
