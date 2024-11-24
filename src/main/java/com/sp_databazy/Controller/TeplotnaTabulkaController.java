package com.sp_databazy.Controller;

import com.sp_databazy.Entity.TeplotnaTabulka;
import com.sp_databazy.Request.UlozTeplotnaTabulkaRequest;
import com.sp_databazy.Service.TeplotnaTabulkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teplotnaTab")
public class TeplotnaTabulkaController {

    private final TeplotnaTabulkaService teplotnaTabulkaService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozTeplotu(@RequestBody UlozTeplotnaTabulkaRequest ulozTeplotnaTabulkaRequest) {
        teplotnaTabulkaService.ulozTeplotu(ulozTeplotnaTabulkaRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeplotnaTabulka getTeplotu(@PathVariable UUID id) {
        return teplotnaTabulkaService.getTeplotu(id);
    }

    @GetMapping("/pacient/{pacientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TeplotnaTabulka> getZoznamTeplotPacient(@PathVariable UUID pacientId) {
        return teplotnaTabulkaService.getZoznamTeplotPacient(pacientId);
    }

    @PutMapping("/{id}")
    public void upravTeplotu(@PathVariable UUID id, @RequestBody TeplotnaTabulka teplotnaTabulka) {
        teplotnaTabulkaService.upravTeplotu(id, teplotnaTabulka);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazTeplotu(@PathVariable UUID id) {
        teplotnaTabulkaService.vymazTeplotu(id);
    }
}
