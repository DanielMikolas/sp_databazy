package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Request.UlozDoktoraRequest;
import com.sp_databazy.Request.UlozSestruRequest;
import com.sp_databazy.Service.DoktorService;
import com.sp_databazy.Service.SestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doktor")
public class DoktorController {

    private final DoktorService doktorService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozDoktora(@RequestBody UlozDoktoraRequest ulozDoktoraRequest){
        doktorService.ulozDoktora(ulozDoktoraRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doktor getDoktor(@PathVariable UUID id) {
        return doktorService.getDoktor(id);
    }

    @GetMapping("/zoznam")
    @ResponseStatus(HttpStatus.OK)
    public List<Doktor> getZoznamDoktorov(){
        return doktorService.getZoznamDoktorov();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazDoktora(@PathVariable UUID id) {
        doktorService.vymazDoktora(id);
    }

    @PutMapping("{id}")
    public void upravDoktora(@PathVariable UUID id, @RequestBody UlozDoktoraRequest ulozDoktoraRequest){
        doktorService.upravDoktora(id, ulozDoktoraRequest);
    }
}
