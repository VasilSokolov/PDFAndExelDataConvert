package com.demo.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import org.apache.poi.hwpf.converter.AbstractWordConverter;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	transform();
//    	try {
//    		InputStream docFile = new FileInputStream(new File("/C:/Work/APIS.docx"));
//    		XWPFDocument doc = new XWPFDocument(docFile);
//    	
////    		OutputStream out = wordToPdf(doc);
//    		OutputStream out = wordToHtml(doc);
//    		doc.close();
//    		out.close();
//    		System.out.println("Done");
//    	}
//    	catch(Exception e) {
//    		e.printStackTrace();
//    	}
    }
    
    private static String wordToHrml(String path) throws IOException {
    	DocumentConverter converter = new DocumentConverter();
    	Result<String> result = converter.convertToHtml(new File(path));
    	String html = result.getValue(); // The generated HTML
    	Set<String> warnings = result.getWarnings(); // Any warnings during conversion
		return html;
    }
    
    private static OutputStream wordToPdf(XWPFDocument doc) throws XWPFConverterException, IOException {
    	PdfOptions pdfOptions = PdfOptions.create();
		OutputStream out = new FileOutputStream(new File("/C:/Work/APIS.pdf"));
		PdfConverter.getInstance().convert(doc, out, pdfOptions);
		return out;
    }
    
    private static OutputStream wordToHtml(XWPFDocument document) throws org.apache.poi.xwpf.converter.core.XWPFConverterException, IOException {
    	//convert .docx to HTML string
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("C:/Work/APIS.docx")));
//        OutputStream out = new ByteArrayOutputStream();
        OutputStream out = new FileOutputStream(new File("/C:/Work/APIS.html"));
//        Document doc = new Document();
//        AbstractWordConverter hml = new WordToHtmlConverter();
        XHTMLConverter.getInstance().convert(document, out, options);
        System.out.println(out);

		return out;
    }
    
    private static OutputStream wordToXml(XWPFDocument document) throws org.apache.poi.xwpf.converter.core.XWPFConverterException, IOException {
    	//convert .docx to HTML string
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("C:/Work/APIS.docx")));
        OutputStream out = new ByteArrayOutputStream();
//        OutputStream out = new FileOutputStream(new File("/C:/Work/file.html"));
		
        XHTMLConverter.getInstance().convert(document, out, options);
        System.out.println(out);
    	
		return out;
    }
    
    public static void transform(){
        String inputFile = "/C:/Work/APIS.docx";
        String outputFile = "/C:/Work/APIS.pdf";

        generatePDF(inputFile, outputFile);

        System.out.println("Done!");
    }

    public static void generatePDF(String inputHtmlPath, String outputPdfPath)
    {
        try {
            String url = new File(inputHtmlPath).toURI().toURL().toString();
            System.out.println("URL: " + url);

            OutputStream out = new FileOutputStream(outputPdfPath);

            //Flying Saucer part
            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocumentFromString(wordToHrml(inputHtmlPath));
            renderer.layout();
            renderer.createPDF(out);
            
            out.close();
        } catch (IOException | com.lowagie.text.DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
