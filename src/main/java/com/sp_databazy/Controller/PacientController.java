package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Request.UlozPacientaRequest;
import com.sp_databazy.Request.UlozSestruRequest;
import com.sp_databazy.Service.PacientService;
import com.sp_databazy.Service.SestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacient")
public class PacientController {

    private final PacientService pacientService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozPacienta(@RequestBody UlozPacientaRequest ulozPacientaRequest){
        pacientService.ulozPacienta(ulozPacientaRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pacient getPacient(@PathVariable UUID id) {
        return pacientService.getPacient(id);
    }

    @GetMapping("/zoznam")
    @ResponseStatus(HttpStatus.OK)
    public List<Pacient> getZoznamPacientov(){
        return pacientService.getZoznamPacientov();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazPacienta(@PathVariable UUID id) {
        pacientService.vymazPacienta(id);
    }

    @PutMapping("{id}")
    public void upravPacienta(@PathVariable UUID id, @RequestBody UlozPacientaRequest ulozPacientaRequest){
        pacientService.upravPacienta(id, ulozPacientaRequest);
    }
}
