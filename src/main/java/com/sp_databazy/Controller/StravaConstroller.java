package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Strava;
import com.sp_databazy.Service.StravaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/strava")
public class StravaConstroller {

    private final StravaService stravaService;

    // Uloženie novej stravy
    @PostMapping("/uloz")
    public ResponseEntity<Strava> createStrava(@RequestBody Strava strava) {
        Strava savedStrava = stravaService.createStrava(strava);
        return new ResponseEntity<>(savedStrava, HttpStatus.CREATED);
    }

    // Získanie stravy podľa ID
    @GetMapping("/{id}")
    public ResponseEntity<Strava> getStravaById(@PathVariable UUID id) {
        Optional<Strava> strava = stravaService.getStravaById(id);
        return strava.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Získanie zoznamu všetkých stráv
    @GetMapping("/zoznam")
    public ResponseEntity<List<Strava>> getAllStrava() {
        List<Strava> stravy = stravaService.getAllStrava();
        return ResponseEntity.ok(stravy);
    }

    // Aktualizácia existujúcej stravy
    @PutMapping("/{id}")
    public ResponseEntity<Strava> updateStrava(@PathVariable UUID id, @RequestBody Strava updatedStrava) {
        Strava updated = stravaService.updateStrava(id, updatedStrava);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Vymazanie stravy podľa ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStrava(@PathVariable UUID id) {
        boolean deleted = stravaService.deleteStrava(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
