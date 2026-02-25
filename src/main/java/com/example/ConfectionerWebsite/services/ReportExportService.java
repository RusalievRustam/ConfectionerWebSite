package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.dto.EnterpriseReport;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class ReportExportService {

    public byte[] exportXlsx(EnterpriseReport r, LocalDate from, LocalDate to) throws IOException {
        try (Workbook wb = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = wb.createSheet("Отчет");
            int row = 0;

            sheet.createRow(row++).createCell(0).setCellValue("Отчет предприятия");
            sheet.createRow(row++).createCell(0).setCellValue("Период: " + from + " - " + to);

            row++;

            row = writeRow(sheet, row, "Продажи", r.getSales());
            row = writeRow(sheet, row, "Закупка сырья", r.getPurchases());
            row = writeRow(sheet, row, "Зарплаты", r.getSalaries());
            row = writeRow(sheet, row, "Кредиты получено", r.getLoansTaken());
            row = writeRow(sheet, row, "Кредиты погашено", r.getLoanPayments());
            row = writeRow(sheet, row, "Прибыль", r.getProfit());

            wb.write(out);
            return out.toByteArray();
        }
    }

    private int writeRow(Sheet sheet, int rowIndex, String name, Double value) {
        Row row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue(name);
        row.createCell(1).setCellValue(value == null ? 0 : value);
        return rowIndex + 1;
    }

    public byte[] exportPdf(EnterpriseReport r, LocalDate from, LocalDate to) throws IOException {
        try (PDDocument doc = new PDDocument();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 750);

                cs.showText("Отчет предприятия");
                cs.newLineAtOffset(0, -20);
                cs.showText("Период: " + from + " - " + to);

                cs.newLineAtOffset(0, -30);
                cs.showText("Продажи: " + r.getSales());
                cs.newLineAtOffset(0, -20);
                cs.showText("Закупка сырья: " + r.getPurchases());
                cs.newLineAtOffset(0, -20);
                cs.showText("Зарплаты: " + r.getSalaries());
                cs.newLineAtOffset(0, -20);
                cs.showText("Кредиты получено: " + r.getLoansTaken());
                cs.newLineAtOffset(0, -20);
                cs.showText("Кредиты погашено: " + r.getLoanPayments());
                cs.newLineAtOffset(0, -20);
                cs.showText("Прибыль: " + r.getProfit());

                cs.endText();
            }

            doc.save(out);
            return out.toByteArray();
        }
    }

    public byte[] exportDocx(EnterpriseReport r, LocalDate from, LocalDate to) throws IOException {
        try (XWPFDocument doc = new XWPFDocument();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            XWPFParagraph title = doc.createParagraph();
            XWPFRun run = title.createRun();
            run.setBold(true);
            run.setFontSize(14);
            run.setText("Отчет предприятия");

            XWPFParagraph p = doc.createParagraph();
            p.createRun().setText("Период: " + from + " - " + to);

            XWPFTable table = doc.createTable();
            addRow(table, "Продажи", r.getSales());
            addRow(table, "Закупка сырья", r.getPurchases());
            addRow(table, "Зарплаты", r.getSalaries());
            addRow(table, "Кредиты получено", r.getLoansTaken());
            addRow(table, "Кредиты погашено", r.getLoanPayments());
            addRow(table, "Прибыль", r.getProfit());

            doc.write(out);
            return out.toByteArray();
        }
    }

    private void addRow(XWPFTable table, String k, Double v) {
        XWPFTableRow row = table.createRow();
        row.getCell(0).setText(k);
        row.getCell(1).setText(String.valueOf(v == null ? 0 : v));
    }

}
