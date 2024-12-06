package com.sp_databazy.Controller;

import com.sp_databazy.Entity.VystupnaDokumentacia;
import com.sp_databazy.Request.UlozVystupnuDokRequest;
import com.sp_databazy.Service.VystupnaDokumentaciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dokumentacia")
public class VystupnaDokumentaciaController {

    @Autowired
    private VystupnaDokumentaciaService service;

    // Vytvorenie novej dokumentácie
    @PostMapping("/uloz")
    public VystupnaDokumentacia createVystupnaDokumentacia(@RequestBody UlozVystupnuDokRequest request) {
        return service.saveVystupnaDokumentacia(request);
    }

    // Získanie všetkých dokumentácií
    @GetMapping("/zoznam")
    public List<VystupnaDokumentacia> getAllVystupnaDokumentacia() {
        return service.findAll();
    }

    // Získanie dokumentácií podľa pacientovho ID
    @GetMapping("/pacient/{pacientId}")
    public List<VystupnaDokumentacia> getVystupnaDokumentaciaByPacient(@PathVariable UUID pacientId) {
        return service.findByPacientId(pacientId);
    }

    // Získanie dokumentácií podľa doktora
    @GetMapping("/lekar/{lekarId}")
    public List<VystupnaDokumentacia> getVystupnaDokumentaciaByLekar(@PathVariable UUID lekarId) {
        return service.findByOsetrujuciLekarId(lekarId);
    }

    // Získanie dokumentácií podľa sestry
    @GetMapping("/sestra/{sestraId}")
    public List<VystupnaDokumentacia> getVystupnaDokumentaciaBySestra(@PathVariable UUID sestraId) {
        return service.findByVystupSestraId(sestraId);
    }

    // Získanie dokumentácií podľa prijmu
    @GetMapping("/prijem/{prijemId}")
    public List<VystupnaDokumentacia> getVystupnaDokumentaciaByPrijem(@PathVariable UUID prijemId) {
        return service.findByPrijemId(prijemId);
    }

    // Aktualizácia dokumentácie
    @PutMapping("/uprav/{id}")
    public VystupnaDokumentacia updateVystupnaDokumentacia(@PathVariable UUID id, @RequestBody UlozVystupnuDokRequest request) {
        return service.updateVystupnaDokumentacia(id, request);
    }

    // Vymazanie dokumentácie
    @DeleteMapping("/{id}")
    public void deleteVystupnaDokumentacia(@PathVariable UUID id) {
        service.deleteVystupnaDokumentacia(id);
    }
}
