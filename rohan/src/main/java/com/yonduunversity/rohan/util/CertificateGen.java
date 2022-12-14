package com.yonduunversity.rohan.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Random;
@Slf4j
public class CertificateGen {

    private static final String CERT_TITLE = "CERTIFICATE OF COMPLETION";
    public static String generateCertificate(String fullname, String code,String courseTitle, long batch) throws FileNotFoundException, DocumentException {
        String fileName = "certificate.pdf";
        File file = new File(fileName);
        FileOutputStream outputStream = new FileOutputStream(file);

        com.itextpdf.text.Document document = new Document(PageSize.A4.rotate(),10f,10f,10f,10f);
        PdfWriter pdfWriter = PdfWriter.getInstance(document,outputStream);
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN,40f);
        Font fontCode = FontFactory.getFont(FontFactory.TIMES_ROMAN,70f);
        Font fontName = FontFactory.getFont(FontFactory.TIMES_ROMAN,16f);

        Paragraph pCertTitle = new Paragraph(150,CERT_TITLE,fontTitle);
        Paragraph pCode = new Paragraph(18, batch + " - " + code,fontCode);
        Paragraph pCourseTitle =new Paragraph(18, courseTitle);
        Paragraph pNameReciver =new Paragraph(18, fullname,fontName);
        Paragraph pDateReceived = new Paragraph(18, String.valueOf(LocalDate.now()));

        pCertTitle.setLeading(100f);
        pCode.setLeading(100f);
        pCourseTitle.setLeading(50f);
        pNameReciver.setLeading(130f);

        pCertTitle.setAlignment(Element.ALIGN_CENTER);
        pCode.setAlignment(Element.ALIGN_CENTER);
        pCourseTitle.setAlignment(Element.ALIGN_CENTER);
        pNameReciver.setAlignment(Element.ALIGN_CENTER);
        pDateReceived.setAlignment(Element.ALIGN_CENTER);

        document.add(pCertTitle);
        document.add(pCode);
        document.add(pCourseTitle);
        document.add(pNameReciver);
        document.add(pDateReceived);

        document.close();
        pdfWriter.close();

        log.info("PDF GENERATED");


        return file.getAbsolutePath();
    }
}
