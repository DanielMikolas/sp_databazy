package com.sp_databazy.Service;

import com.sp_databazy.Entity.Diagnoza;
import com.sp_databazy.Repository.DiagnozaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnozaImportService {

    private final DiagnozaRepository diagnozaRepository;

    public List<Diagnoza> importDiagnosesFromPdf(MultipartFile file) throws IOException {
        List<Diagnoza> importedDiagnoses = new ArrayList<>();

        // Načíta obsah PDF
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        // Parsovanie údajov
        String[] lines = text.split("\n");
        boolean isFirstLine = true; // Premenná na preskakovanie prvého riadku

        for (String line : lines) {
            if (isFirstLine) {
                isFirstLine = false; // Preskočí prvý riadok
                continue;
            }

            String[] parts = parseLine(line);

            if (parts != null) {
                String kod = parts[0];
                String nazov = parts[1];

                Diagnoza diagnoza = new Diagnoza();
                diagnoza.setKod(kod);
                diagnoza.setNazov(nazov);
                importedDiagnoses.add(diagnoza);
            }
        }

        // Uloženie do databázy
        return diagnozaRepository.saveAll(importedDiagnoses);
    }

    private String[] parseLine(String line) {
        // Predpoklad: formát je "Kód Názov"
        // Parsuje sa na prvý whitespace
        String[] parts = line.split(" ", 2);

        if (parts.length == 2) {
            String kod = parts[0].trim();
            String nazov = parts[1].trim();

            if (!kod.isEmpty() && !nazov.isEmpty()) {
                return new String[]{kod, nazov};
            }
        }

        return null;
    }
}
