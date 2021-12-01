package com.example.ltmck.model.BO;


import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

import java.io.File;


public class ConvertBO {

  public static void cvPdfToDocx(File source, File output) {
    System.out.println("Starting convert pdf file...");
    try {
      Document pdf = new Document(source.getAbsolutePath());
      pdf.save(output.getAbsolutePath(), SaveFormat.DocX);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void cvDocToPdf(File source, File output) {
    Document doc = new Document(source.getAbsolutePath());
    doc.save(output.getAbsolutePath(), SaveFormat.Pdf);
  }

  public static void test2() {
    Document doc = new Document("E:\\.WORK\\HK7\\LTM\\ltm_ck\\src\\main\\resources\\pdf\\giai.pdf");
    doc.save("E:\\.WORK\\HK7\\LTM\\ltm_ck\\src\\main\\resources\\doc\\giai.docx", SaveFormat.DocX);
  }
}

