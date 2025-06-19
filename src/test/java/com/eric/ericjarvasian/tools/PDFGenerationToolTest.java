package com.eric.ericjarvasian.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool pdfGenerationTool = new PDFGenerationTool();
        String result = pdfGenerationTool.generatePDF("test.pdf", "This is a test PDF.");
        Assertions.assertNotNull( result);
    }
}