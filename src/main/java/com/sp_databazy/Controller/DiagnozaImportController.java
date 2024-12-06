package com.sp_databazy.Controller;


import com.sp_databazy.Entity.Diagnoza;
import com.sp_databazy.Service.DiagnozaImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diagnoza/import")
public class DiagnozaImportController {
    private final DiagnozaImportService diagnozaImportService;

    @PostMapping
    public List<Diagnoza> importDiagnoses(@RequestParam("file") MultipartFile file) throws IOException {
        return diagnozaImportService.importDiagnosesFromPdf(file);
    }
}
