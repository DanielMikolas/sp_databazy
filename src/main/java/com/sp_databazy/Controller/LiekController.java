package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Liek;
import com.sp_databazy.Repository.LiekRepository;
import com.sp_databazy.Service.LiekService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lieky")
public class LiekController {

    @Autowired
    private LiekService liekService;

    @GetMapping("/synchronizuj-lieky")
    public String synchronizujLieky() {
        try {
            liekService.synchronizujLieky();
            return "Synchronizácia úspešná!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Chyba počas synchronizácie: " + e.getMessage();
        }
    }

    // Endpoint na získanie lieku podľa SUKL kódu
    @GetMapping("/{suklKod}")
    public ResponseEntity<Liek> getLiekBySuklKod(@PathVariable String suklKod) {
        Optional<Liek> liek = liekService.getLiekBySuklKod(suklKod);
        return liek.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint na získanie zoznamu všetkých liekov
    @GetMapping("/zoznam")
    public ResponseEntity<List<Liek>> getAllLieky() {
        List<Liek> lieky = liekService.getAllLieky();
        return ResponseEntity.ok(lieky);
    }
}
