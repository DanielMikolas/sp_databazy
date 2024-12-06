package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Diagnoza;
import com.sp_databazy.Request.UlozDiagnozuRequest;
import com.sp_databazy.Service.DiagnozaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diagnoza")
public class DiagnozaController {

    private final DiagnozaService diagnozaService;

    @GetMapping("/zoznam")
    public List<Diagnoza> getAllDiagnoses() {
        return diagnozaService.getAllDiagnoses();
    }

    @GetMapping("/{kod}")
    public Diagnoza getDiagnoseById(@PathVariable String kod) {
        return diagnozaService.getDiagnoseById(kod);
    }

    @PostMapping("/uloz")
    public Diagnoza createDiagnose(@RequestBody UlozDiagnozuRequest request) {
        return diagnozaService.createDiagnose(request);
    }


    @DeleteMapping("/{kod}")
    public void deleteDiagnose(@PathVariable String kod) {
        diagnozaService.deleteDiagnose(kod);
    }
}
