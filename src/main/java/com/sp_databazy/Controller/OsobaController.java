package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Osoba;
import com.sp_databazy.Request.UlozOsobaRequest;
import com.sp_databazy.Service.OsobaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/osoba")
public class OsobaController {

    private final OsobaService osobaService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozOsobu(@RequestBody UlozOsobaRequest ulozOsobaRequest){
        osobaService.ulozOsobu(ulozOsobaRequest);
    }

    @GetMapping("/{rodneCislo}")
    @ResponseStatus(HttpStatus.OK)
    public Osoba getOsoba(@PathVariable String rodneCislo) {
        return osobaService.getOsoba(rodneCislo);
    }

    @GetMapping("/zoznam")
    @ResponseStatus(HttpStatus.OK)
    public List<Osoba> getZoznamOsob(){
        return osobaService.getZoznam();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazOsobu(@PathVariable UUID id) {
        osobaService.vymazOsobu(id);
    }

    @PutMapping("{id}")
    public void upravOsobu(@PathVariable UUID id, @RequestBody UlozOsobaRequest ulozOsobaRequest){
        osobaService.upravOsobu(id, ulozOsobaRequest);
    }
}
