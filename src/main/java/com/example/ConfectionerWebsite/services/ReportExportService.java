package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.dto.EnterpriseReport;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
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

            int rowIndex = 0;

            // ===== Заголовок отчета =====
            Row titleRow = sheet.createRow(rowIndex++);
            titleRow.createCell(0).setCellValue("Отчет предприятия");

            Row periodRow = sheet.createRow(rowIndex++);
            periodRow.createCell(0).setCellValue("Период: " + from + " - " + to);

            rowIndex++; // пустая строка

            // ===== Строка с названиями колонок =====
            Row header = sheet.createRow(rowIndex++);

            header.createCell(0).setCellValue("Продажи");
            header.createCell(1).setCellValue("Закупка сырья");
            header.createCell(2).setCellValue("Зарплаты");
            header.createCell(3).setCellValue("Кредиты получено");
            header.createCell(4).setCellValue("Кредиты погашено");
            header.createCell(5).setCellValue("Прибыль");

            // Делаем заголовки жирными
            var boldFont = wb.createFont();
            boldFont.setBold(true);

            var headerStyle = wb.createCellStyle();
            headerStyle.setFont(boldFont);

            for (int i = 0; i < 6; i++) {
                header.getCell(i).setCellStyle(headerStyle);
            }

            // ===== Строка с данными =====
            Row dataRow = sheet.createRow(rowIndex);

            dataRow.createCell(0).setCellValue(nz(r.getSales()));
            dataRow.createCell(1).setCellValue(nz(r.getPurchases()));
            dataRow.createCell(2).setCellValue(nz(r.getSalaries()));
            dataRow.createCell(3).setCellValue(nz(r.getLoansTaken()));
            dataRow.createCell(4).setCellValue(nz(r.getLoanPayments()));
            dataRow.createCell(5).setCellValue(nz(r.getProfit()));

            // ===== Авторазмер колонок =====
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            wb.write(out);
            return out.toByteArray();
        }
    }

    private double nz(Double v) {
        return v == null ? 0 : v;
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

            // Загрузи кириллический шрифт как ты уже сделал раньше:
            byte[] fontBytes;
            try (var is = getClass().getResourceAsStream("/fonts/DejaVuSans.ttf")) {
                if (is == null) throw new IllegalStateException("Не найден шрифт /fonts/DejaVuSans.ttf");
                fontBytes = is.readAllBytes();
            }
            var font = org.apache.pdfbox.pdmodel.font.PDType0Font.load(
                    doc, new java.io.ByteArrayInputStream(fontBytes), true
            );

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

                float x = 50;
                float y = 760;
                float line = 18;

                // ===== Заголовок =====
                cs.beginText();
                cs.setFont(font, 18);
                cs.newLineAtOffset(x, y);
                cs.showText("Отчет по деятельности кондитерского цеха");
                cs.endText();

                y -= 28;

                // ===== Период =====
                cs.beginText();
                cs.setFont(font, 12);
                cs.newLineAtOffset(x, y);
                cs.showText("Период: " + from + " — " + to);
                cs.endText();

                y -= 25;

                // ===== Описание (2-3 строки) =====
                y = writeParagraph(cs, font, 11, x, y,
                        "Данный документ является внутренним отчетом предприятия по кондитерскому производству.",
                        520, 14);
                y = writeParagraph(cs, font, 11, x, y,
                        "Отчет отражает продажи, закупки сырья, выплаты зарплат, движение кредитов и итоговую прибыль.",
                        520, 14);

                y -= 10;

                // ===== Линия =====
                cs.moveTo(x, y);
                cs.lineTo(x + 500, y);
                cs.stroke();

                y -= 22;

                // ===== “Таблица” =====
                cs.beginText();
                cs.setFont(font, 12);
                cs.newLineAtOffset(x, y);
                cs.showText("Показатели:");
                cs.endText();

                y -= 18;

                y = writeKeyValue(cs, font, x, y, "Продажи", r.getSales());
                y = writeKeyValue(cs, font, x, y, "Закупка сырья", r.getPurchases());
                y = writeKeyValue(cs, font, x, y, "Зарплаты", r.getSalaries());
                y = writeKeyValue(cs, font, x, y, "Кредиты получено", r.getLoansTaken());
                y = writeKeyValue(cs, font, x, y, "Кредиты погашено", r.getLoanPayments());

                y -= 8;

                // ===== Итог =====
                cs.moveTo(x, y);
                cs.lineTo(x + 500, y);
                cs.stroke();
                y -= 20;

                cs.beginText();
                cs.setFont(font, 14);
                cs.newLineAtOffset(x, y);
                cs.showText("Итоговая прибыль: " + formatMoney(r.getProfit()) + " сом");
                cs.endText();

                // ===== Подпись =====
                y -= 40;
                cs.beginText();
                cs.setFont(font, 10);
                cs.newLineAtOffset(x, y);
                cs.showText("Сформировано: " + LocalDate.now() + "    |    Система учета кондитерского предприятия");
                cs.endText();
            }

            doc.save(out);
            return out.toByteArray();
        }
    }

    private float writeKeyValue(PDPageContentStream cs,
                                org.apache.pdfbox.pdmodel.font.PDFont font,
                                float x, float y, String key, Double value) throws IOException {
        cs.beginText();
        cs.setFont(font, 11);
        cs.newLineAtOffset(x, y);
        cs.showText(key + ":");
        cs.endText();

        cs.beginText();
        cs.setFont(font, 11);
        cs.newLineAtOffset(x + 260, y);
        cs.showText(formatMoney(value) + " сом");
        cs.endText();

        return y - 16;
    }

    private String formatMoney(Double v) {
        if (v == null) return "0.00";
        return String.format(java.util.Locale.US, "%.2f", v);
    }

    // Очень простой перенос текста по ширине (чтобы описание не вылезало)
    private float writeParagraph(PDPageContentStream cs,
                                 org.apache.pdfbox.pdmodel.font.PDFont font,
                                 int fontSize,
                                 float x, float y,
                                 String text,
                                 float maxWidth,
                                 float lineHeight) throws IOException {
        var words = text.split("\\s+");
        StringBuilder line = new StringBuilder();

        for (String w : words) {
            String test = line.length() == 0 ? w : line + " " + w;
            float width = font.getStringWidth(test) / 1000 * fontSize;

            if (width > maxWidth) {
                cs.beginText();
                cs.setFont(font, fontSize);
                cs.newLineAtOffset(x, y);
                cs.showText(line.toString());
                cs.endText();
                y -= lineHeight;
                line = new StringBuilder(w);
            } else {
                line = new StringBuilder(test);
            }
        }

        if (line.length() > 0) {
            cs.beginText();
            cs.setFont(font, fontSize);
            cs.newLineAtOffset(x, y);
            cs.showText(line.toString());
            cs.endText();
            y -= lineHeight;
        }

        return y;
    }


    public byte[] exportDocx(EnterpriseReport r, LocalDate from, LocalDate to) throws IOException {
        try (XWPFDocument doc = new XWPFDocument();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // ===== Заголовок =====
            XWPFParagraph title = doc.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setBold(true);
            titleRun.setFontSize(18);
            titleRun.setText("Отчет по деятельности кондитерского цеха");

            // ===== Подзаголовок =====
            XWPFParagraph period = doc.createParagraph();
            period.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun periodRun = period.createRun();
            periodRun.setFontSize(12);
            periodRun.setText("Период: " + from + " — " + to);

            // ===== Описание =====
            XWPFParagraph desc = doc.createParagraph();
            desc.setAlignment(ParagraphAlignment.BOTH);
            XWPFRun descRun = desc.createRun();
            descRun.setFontSize(12);
            descRun.setText(
                    "Данный документ является внутренним отчетом предприятия по кондитерскому производству. " +
                            "Он отражает ключевые финансовые показатели: продажи готовой продукции, закупки сырья, " +
                            "выплату заработной платы, движение кредитных средств и итоговую прибыль за выбранный период."
            );

            doc.createParagraph(); // пустая строка

            // ===== Таблица =====
            XWPFTable table = doc.createTable(1, 2);
            table.setWidth("100%");

            // Заголовки
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("Показатель");
            header.getCell(1).setText("Сумма (сом)");

            addRow(table, "Продажи", r.getSales());
            addRow(table, "Закупка сырья", r.getPurchases());
            addRow(table, "Зарплаты", r.getSalaries());
            addRow(table, "Кредиты получено", r.getLoansTaken());
            addRow(table, "Кредиты погашено", r.getLoanPayments());
            addRow(table, "Прибыль", r.getProfit());

            doc.createParagraph(); // пустая строка

            // ===== Подпись / Дата =====
            XWPFParagraph footer = doc.createParagraph();
            footer.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun footerRun = footer.createRun();
            footerRun.setFontSize(11);
            footerRun.setItalic(true);
            footerRun.setText("Сформировано: " + LocalDate.now());

            doc.write(out);
            return out.toByteArray();
        }
    }

    private void addRow(XWPFTable table, String k, Double v) {
        XWPFTableRow row = table.createRow();
        row.getCell(0).setText(k);
        row.getCell(1).setText(String.format("%.2f", v == null ? 0 : v));
    }
}
