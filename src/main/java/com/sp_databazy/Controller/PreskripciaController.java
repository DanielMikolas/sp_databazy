package com.sp_databazy.Controller;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.UnicodeUtil;
import com.sp_databazy.Entity.Preskripcia;
import com.sp_databazy.Request.UlozPreskripciaRequest;
import com.sp_databazy.Service.PreskripciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/preskripcia")
public class PreskripciaController {

    private final PreskripciaService preskripciaService;

    // Získanie zoznamu všetkých predpisov
    @GetMapping("/zoznam")
    public ResponseEntity<List<Preskripcia>> getAllPreskripcie() {
        return ResponseEntity.ok(preskripciaService.getAllPreskripcie());
    }

    // Získanie predpisu podľa ID
    @GetMapping("/{id}")
    public ResponseEntity<Preskripcia> getPreskripciaById(@PathVariable UUID id) {
        return preskripciaService.getPreskripciaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Získanie predpisov podľa ID pacienta
    @GetMapping("/pacient/{pacientId}")
    public ResponseEntity<List<Preskripcia>> getPreskripcieByPacientId(@PathVariable UUID pacientId) {
        return ResponseEntity.ok(preskripciaService.getPreskripcieByPacientId(pacientId));
    }

    // Uloženie nového predpisu
    @PostMapping("/uloz")
    public ResponseEntity<Preskripcia> createPreskripcia(@RequestBody UlozPreskripciaRequest request) {
        return ResponseEntity.ok(preskripciaService.createPreskripcia(request));
    }

    // Aktualizácia dávkovania predpisu
    @PutMapping("/uprav/{id}")
    public ResponseEntity<Preskripcia> updateDavkovanie(@PathVariable UUID id, @RequestBody UlozPreskripciaRequest ulozPreskripciaRequest) {
        return ResponseEntity.ok(preskripciaService.updateDavkovanie(id, ulozPreskripciaRequest));
    }

    // Vymazanie predpisu podľa ID
    @DeleteMapping("/vymaz/{id}")
    public ResponseEntity<Void> deletePreskripcia(@PathVariable UUID id) {
        preskripciaService.deletePreskripciaById(id);
        return ResponseEntity.noContent().build();
    }
}
