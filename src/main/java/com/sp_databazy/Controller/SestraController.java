package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Osoba;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Request.UlozOsobaRequest;
import com.sp_databazy.Request.UlozSestruRequest;
import com.sp_databazy.Service.SestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sestra")
public class SestraController {

    private final SestraService sestraService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozSestru(@RequestBody UlozSestruRequest ulozSestruRequest){
        sestraService.ulozSestru(ulozSestruRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sestra getSestra(@PathVariable UUID id) {
        return sestraService.getSestra(id);
    }

    @GetMapping("/zoznam")
    @ResponseStatus(HttpStatus.OK)
    public List<Sestra> getZoznamSestier(){
        return sestraService.getZoznamSestri();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazSestru(@PathVariable UUID id) {
        sestraService.vymazSestru(id);
    }

    @PutMapping("{id}")
    public void upravSestru(@PathVariable UUID id, @RequestBody UlozSestruRequest ulozSestruRequest){
        sestraService.upravSestru(id, ulozSestruRequest);
    }
}
