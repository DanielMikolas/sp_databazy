package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Oddelenie;
import com.sp_databazy.Request.UlozOddelenieRequest;
import com.sp_databazy.Service.OddelenieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oddelenie")
public class OddelenieController {

    private final OddelenieService oddelenieService;

    @PostMapping("/uloz")
    public ResponseEntity<?> ulozOddelenie(@RequestBody UlozOddelenieRequest request) {
        try {
            Oddelenie ulozeneOddelenie = oddelenieService.ulozOddelenie(request);
            return ResponseEntity.ok(ulozeneOddelenie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/zoznam")
    public ResponseEntity<List<Oddelenie>> getVsetkyOddelenia() {
        return ResponseEntity.ok(oddelenieService.getVsetkyOddelenia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOddelenieById(@PathVariable UUID id) {
        try {
            Oddelenie oddelenie = oddelenieService.getOddelenieById(id);
            return ResponseEntity.ok(oddelenie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/doktor/{id}")
    public ResponseEntity<?> getOddelenieByVeduciDoktorId(@PathVariable UUID id) {
        try {
            Oddelenie oddelenie = oddelenieService.getOddelenieByVeduciDoktorId(id);
            return ResponseEntity.ok(oddelenie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> upravOddelenie(@PathVariable UUID id, @RequestBody UlozOddelenieRequest request) {
        try {
            Oddelenie upraveneOddelenie = oddelenieService.upravOddelenie(id, request);
            return ResponseEntity.ok(upraveneOddelenie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> vymazOddelenie(@PathVariable UUID id) {
        try {
            oddelenieService.vymazOddelenie(id);
            return ResponseEntity.ok("Oddelenie bolo úspešne vymazané.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
