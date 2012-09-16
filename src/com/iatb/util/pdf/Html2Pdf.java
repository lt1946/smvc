package com.iatb.util.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class Html2Pdf {
//	private static void addFonts() throws DocumentException, IOException{
//		if(null == renderer) {
//			return;
//		}
//		
//        // 添加所需的字体
//        ITextFontResolver fontResolver = renderer.getFontResolver(); 
//
//        URL fontsUrl = Html2Pdf.class.getResource("/com/hank/fonts/");//该文件夹下放所需字体文件
//        File fonts = new File(fontsUrl.getPath());
//        File[] fileList = fonts.listFiles();
//        for(int i=0; i < fileList.length; i++){
//        	fontResolver.addFont(fileList[i].getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        }
//        
//	}
	
	public static String print2Pdf(String inputFile) {
        String url = null;
		try {
			url = new File(inputFile).toURI().toURL().toString();
		} catch (MalformedURLException e) {
			return null;
		}


		String outputFile = inputFile.substring(0, inputFile.lastIndexOf(".")) + ".pdf";

        OutputStream os = null;
		try {
			os = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			
			return null;
		}

        ITextRenderer renderer = null;
		try {
			renderer = new ITextRenderer();
		} catch (Exception e) {
			return null;
		}
		
        renderer.setDocument(url);
        
        // 解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL("file:/D:/working/HtmlTemp/image/");
        
        renderer.layout();
        try {
			renderer.createPDF(os);
		} catch (DocumentException e) {
			return null;
		}
        
        try {
			os.close();
		} catch (IOException e) {
			return null;
		}
        
        return outputFile;
	}

        public static void main(String args[]){
            String inputFile = "C:/Documents and Settings/Administrator/My Documents/1.htm"; //必须符合W3C标准
            Html2Pdf.print2Pdf(inputFile);
        }
}

