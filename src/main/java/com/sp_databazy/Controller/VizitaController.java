package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Oddelenie;
import com.sp_databazy.Entity.Vizita;
import com.sp_databazy.Request.UlozOddelenieRequest;
import com.sp_databazy.Request.UlozVizituRequest;
import com.sp_databazy.Service.VizitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vizita")
public class VizitaController {

    private final VizitaService vizitaService;

    // Uloženie novej vizity
    @PostMapping("/uloz")
    public ResponseEntity<Vizita> ulozVizitu(@RequestBody UlozVizituRequest ulozVizituRequest) {
        Vizita vizita = vizitaService.ulozVizitu(ulozVizituRequest);
        return ResponseEntity.ok(vizita);
    }

    // Získanie vizity podľa ID
    @GetMapping("/{id}")
    public ResponseEntity<Vizita> getVizitaById(@PathVariable UUID id) {
        Optional<Vizita> vizita = vizitaService.getVizitaById(id);
        return vizita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Získanie všetkých vizít
    @GetMapping("/zoznam")
    public ResponseEntity<List<Vizita>> getAllVizity() {
        List<Vizita> vizity = vizitaService.getAllVizity();
        return ResponseEntity.ok(vizity);
    }

    // Vymazanie vizity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVizita(@PathVariable UUID id) {
        if (vizitaService.deleteVizita(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    // Úprava informácií o vizite
//    @PutMapping("/uprav/{id}")
//    public ResponseEntity<Vizita> upravVizitu(@PathVariable UUID id, @RequestBody UlozVizituRequest ulozVizituRequest) {
//        // Volanie služby na aktualizáciu vizity
//        Vizita updatedVizita = vizitaService.upravVizitu(id, ulozVizituRequest);
//        return ResponseEntity.ok(updatedVizita);
//    }

    @PutMapping("/uprav/{id}")
    public ResponseEntity<?> upravVizitu(@PathVariable UUID id, @RequestBody UlozVizituRequest request) {
        try {
            Vizita upravenaVizita = vizitaService.upravVizitu(id, request);
            return ResponseEntity.ok(upravenaVizita);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
