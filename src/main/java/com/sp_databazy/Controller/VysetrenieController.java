package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Vysetrenie;
import com.sp_databazy.Request.UlozVysetrenieRequest;
import com.sp_databazy.Service.VysetrenieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vysetrenie")
public class VysetrenieController {

    private final VysetrenieService vysetrenieService;

    // Uloženie nového vyšetrenia
    @PostMapping("/uloz")
    public ResponseEntity<Vysetrenie> ulozVysetrenie(@RequestBody UlozVysetrenieRequest request) {
        Vysetrenie noveVysetrenie = vysetrenieService.ulozVysetrenie(request);
        return ResponseEntity.ok(noveVysetrenie);
    }

    // Získanie zoznamu všetkých vyšetrení
    @GetMapping("/zoznam")
    public ResponseEntity<List<Vysetrenie>> zoznamVysetreni() {
        List<Vysetrenie> vysetrenia = vysetrenieService.zoznamVysetreni();
        return ResponseEntity.ok(vysetrenia);
    }

    // Získanie vyšetrenia podľa ID
    @GetMapping("/{id}")
    public ResponseEntity<Vysetrenie> ziskajVysetrenie(@PathVariable UUID id) {
        Vysetrenie vysetrenie = vysetrenieService.ziskajVysetrenie(id);
        return ResponseEntity.ok(vysetrenie);
    }

    // Získanie vyšetrení podľa ID pacienta
    @GetMapping("/pacient/{pacientId}")
    public ResponseEntity<List<Vysetrenie>> zoznamVysetreniPacienta(@PathVariable UUID pacientId) {
        List<Vysetrenie> vysetrenia = vysetrenieService.zoznamVysetreniPacienta(pacientId);
        return ResponseEntity.ok(vysetrenia);
    }

    // Úprava existujúceho vyšetrenia
    @PutMapping("/{id}")
    public ResponseEntity<Vysetrenie> upravVysetrenie(@PathVariable UUID id, @RequestBody UlozVysetrenieRequest request) {
        Vysetrenie upraveneVysetrenie = vysetrenieService.upravVysetrenie(id, request);
        return ResponseEntity.ok(upraveneVysetrenie);
    }

    // Vymazanie vyšetrenia podľa ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> vymazVysetrenie(@PathVariable UUID id) {
        vysetrenieService.vymazVysetrenie(id);
        return ResponseEntity.noContent().build();
    }
}
