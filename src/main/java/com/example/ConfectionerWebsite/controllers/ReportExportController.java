package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.dto.EnterpriseReport;
import com.example.ConfectionerWebsite.services.ReportExportService;
import com.example.ConfectionerWebsite.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class ReportExportController {

    private final ReportService reportService;
    private final ReportExportService reportExportService;

    @GetMapping("/reports/export")
    public ResponseEntity<byte[]> export(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam String format
    ) throws IOException {

        EnterpriseReport report = reportService.build(from, to);

        byte[] file;
        String contentType;
        String filename;

        switch (format.toLowerCase()) {
            case "xlsx" -> {
                file = reportExportService.exportXlsx(report, from, to);
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                filename = "report.xlsx";
            }
            case "pdf" -> {
                file = reportExportService.exportPdf(report, from, to);
                contentType = "application/pdf";
                filename = "report.pdf";
            }
            case "docx" -> {
                file = reportExportService.exportDocx(report, from, to);
                contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                filename = "report.docx";
            }
            default -> throw new RuntimeException("Неизвестный формат: " + format);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType(contentType))
                .body(file);
    }
}