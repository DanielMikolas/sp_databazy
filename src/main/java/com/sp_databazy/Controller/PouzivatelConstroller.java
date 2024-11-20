package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Osoba;
import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Request.UlozOsobaRequest;
import com.sp_databazy.Request.UlozPouzivatelaRequest;
import com.sp_databazy.Service.PouzivatelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pouzivatel")
public class PouzivatelConstroller {

    private final PouzivatelService pouzivatelService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozPouzivatela(@RequestBody UlozPouzivatelaRequest ulozPouzivatelaRequest){
        pouzivatelService.ulozPouzivatela(ulozPouzivatelaRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pouzivatel getPouzivatel(@PathVariable UUID id) {
        return pouzivatelService.getPouzivatel(id);
    }

    @GetMapping("/zoznam")
    @ResponseStatus(HttpStatus.OK)
    public List<Pouzivatel> getZoznamPouzivatelov(){
        return pouzivatelService.getZoznamPouzivatelov();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazPouzivatela(@PathVariable UUID id) {
        pouzivatelService.vymazPouzivatela(id);
    }

    @PutMapping("{id}")
    public void upravPouzivatela(@PathVariable UUID id, @RequestBody UlozPouzivatelaRequest ulozPouzivatelaRequest){
        pouzivatelService.upravPouzivatela(id, ulozPouzivatelaRequest);
    }
}
