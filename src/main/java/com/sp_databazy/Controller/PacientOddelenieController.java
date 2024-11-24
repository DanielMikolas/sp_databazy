package com.sp_databazy.Controller;

import com.sp_databazy.Entity.PacientOddelenie;
import com.sp_databazy.Request.UlozPacientOddelenieRequest;
import com.sp_databazy.Service.PacientOddelenieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacOddelenie")
public class PacientOddelenieController {

    private final PacientOddelenieService pacientOddelenieService;

    @PostMapping("/uloz")
    public ResponseEntity<PacientOddelenie> ulozPacientOddelenie(@RequestBody UlozPacientOddelenieRequest request) {
        return ResponseEntity.ok(pacientOddelenieService.ulozPacientOddelenie(request));
    }

    @GetMapping("/zoznam")
    public ResponseEntity<List<PacientOddelenie>> getVsetkyPacientOddelenia() {
        return ResponseEntity.ok(pacientOddelenieService.getVsetkyPacientOddelenia());
    }
    // Získanie záznamov podľa ID oddelenia
    @GetMapping("/oddelenie/{oddelenieId}")
    public ResponseEntity<List<PacientOddelenie>> getZaznamyPodlaOddelenia(@PathVariable UUID oddelenieId) {
        List<PacientOddelenie> zaznamy = pacientOddelenieService.getZaznamyPodlaOddelenia(oddelenieId);
        return ResponseEntity.ok(zaznamy);
    }

    // Získanie záznamov podľa ID pacienta
    @GetMapping("/pacient/{pacientId}")
    public ResponseEntity<List<PacientOddelenie>> getZaznamyPodlaPacienta(@PathVariable UUID pacientId) {
        List<PacientOddelenie> zaznamy = pacientOddelenieService.getZaznamyPodlaPacienta(pacientId);
        return ResponseEntity.ok(zaznamy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacientOddelenie> getPacientOddelenieById(@PathVariable UUID id) {
        return ResponseEntity.ok(pacientOddelenieService.getPacientOddelenieById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacientOddelenie> upravPacientOddelenie(
            @PathVariable UUID id,
            @RequestBody UlozPacientOddelenieRequest request
    ) {
        return ResponseEntity.ok(pacientOddelenieService.upravPacientOddelenie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> vymazPacientOddelenie(@PathVariable UUID id) {
        pacientOddelenieService.vymazPacientOddelenie(id);
        return ResponseEntity.noContent().build();
    }
}
