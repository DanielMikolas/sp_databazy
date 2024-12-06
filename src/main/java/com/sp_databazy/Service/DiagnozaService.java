package com.sp_databazy.Service;

import com.sp_databazy.Entity.Diagnoza;
import com.sp_databazy.Repository.DiagnozaRepository;
import com.sp_databazy.Request.UlozDiagnozuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnozaService {

    private final DiagnozaRepository diagnozaRepository;

    public List<Diagnoza> getAllDiagnoses() {
        return diagnozaRepository.findAll();
    }

    public Diagnoza getDiagnoseById(String kod) {
        return diagnozaRepository.findById(kod)
                .orElseThrow(() -> new RuntimeException("Diagnóza s kódom " + kod + " neexistuje"));
    }

    public Diagnoza createDiagnose(UlozDiagnozuRequest request) {
        Diagnoza diagnoza = new Diagnoza();
        diagnoza.setKod(request.getKod());
        diagnoza.setNazov(request.getNazov());
        return diagnozaRepository.save(diagnoza);
    }


    public void deleteDiagnose(String kod) {
        Diagnoza existingDiagnose = getDiagnoseById(kod);
        diagnozaRepository.delete(existingDiagnose);
    }
}
