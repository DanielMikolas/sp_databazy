package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Prijem;
import com.sp_databazy.Request.UlozPrijemRequest;
import com.sp_databazy.Service.PrijemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prijem")
public class PrijemController {

    private final PrijemService prijemService;

    @PostMapping("/uloz")
    public ResponseEntity<Prijem> ulozPrijem(@RequestBody UlozPrijemRequest request) {
        Prijem prijem = prijemService.ulozPrijem(request);
        return ResponseEntity.ok(prijem);
    }

    @GetMapping("/zoznam")
    public ResponseEntity<List<Prijem>> vsetkyPrijmy() {
        return ResponseEntity.ok(prijemService.vsetkyPrijmy());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prijem> najdiPrijemPodlaId(@PathVariable UUID id) {
        return ResponseEntity.ok(prijemService.najdiPrijemPodlaId(id));
    }

    @GetMapping("/pacient/{pacientId}")
    public ResponseEntity<List<Prijem>> najdiPrijemPodlaPacienta(@PathVariable UUID pacientId) {
        return ResponseEntity.ok(prijemService.najdiPrijemPodlaPacienta(pacientId));
    }

    @GetMapping("/lekar/{lekarId}")
    public ResponseEntity<List<Prijem>> najdiPrijemPodlaLekara(@PathVariable UUID lekarId) {
        return ResponseEntity.ok(prijemService.najdiPrijemPodlaLekara(lekarId));
    }

    @GetMapping("/sestra/{sestraId}")
    public ResponseEntity<List<Prijem>> najdiPrijemPodlaSestry(@PathVariable UUID sestraId) {
        return ResponseEntity.ok(prijemService.najdiPrijemPodlaSestry(sestraId));
    }

    @GetMapping("/oddelenie/{oddelenieId}")
    public ResponseEntity<List<Prijem>> najdiPrijemPodlaOddelenia(@PathVariable UUID oddelenieId) {
        return ResponseEntity.ok(prijemService.najdiPrijemPodlaOddelenia(oddelenieId));
    }

    @PutMapping("/uprav/{id}")
    public ResponseEntity<Prijem> upravPrijem(@PathVariable UUID id, @RequestBody UlozPrijemRequest request) {
        Prijem prijem = prijemService.upravPrijem(id, request);
        return ResponseEntity.ok(prijem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> vymazPrijem(@PathVariable UUID id) {
        prijemService.vymazPrijem(id);
        return ResponseEntity.noContent().build();
    }
}
