package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Poistovna;
import com.sp_databazy.Request.UlozPoistovnuRequest;
import com.sp_databazy.Service.PoistovnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poistovna")
@RequiredArgsConstructor
public class PoistovnaController {

    private final PoistovnaService poistovnaService;

    // Uloženie poisťovne
    @PostMapping("/save")
    public ResponseEntity<Poistovna> ulozPoistovnu(@RequestBody UlozPoistovnuRequest ulozPoistovnuRequest) {
        Poistovna savedPoistovna = poistovnaService.ulozPoistovnu(ulozPoistovnuRequest);
        return new ResponseEntity<>(savedPoistovna, HttpStatus.CREATED);
    }

    // Získanie poisťovne podľa čísla poisťovne
    @GetMapping("/{cisloPois}")
    public ResponseEntity<Poistovna> getPoistovna(@PathVariable String cisloPois) {
        Poistovna poistovna = poistovnaService.getPoistovna(cisloPois);
        return new ResponseEntity<>(poistovna, HttpStatus.OK);
    }

    // Získanie zoznamu všetkých poisťovní
    @GetMapping("/all")
    public ResponseEntity<List<Poistovna>> getZoznamPoistovni() {
        List<Poistovna> poistovne = poistovnaService.getZoznamPoistovni();
        return new ResponseEntity<>(poistovne, HttpStatus.OK);
    }

    // Úprava poisťovne podľa čísla poisťovne
    @PutMapping("/{cisloPois}")
    public ResponseEntity<Poistovna> upravPoistovnu(
            @PathVariable String cisloPois,
            @RequestBody UlozPoistovnuRequest ulozPoistovnuRequest) {
        Poistovna updatedPoistovna = poistovnaService.upravPoistovnu(cisloPois, ulozPoistovnuRequest);
        return new ResponseEntity<>(updatedPoistovna, HttpStatus.OK);
    }

    // Vymazanie poisťovne
    @DeleteMapping("/{cisloPois}")
    public ResponseEntity<Void> vymazPoistovnu(@PathVariable String cisloPois) {
        poistovnaService.vymazPoistovnu(cisloPois);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
