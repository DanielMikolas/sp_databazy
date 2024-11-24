package com.sp_databazy.Controller;

import com.sp_databazy.Entity.DennePoznamky;
import com.sp_databazy.Request.UlozDennePoznamkyRequest;
import com.sp_databazy.Service.DennePoznamkyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dennePoznamky")
public class DennePoznamkyController {

    private final DennePoznamkyService dennePoznamkyService;

    @PostMapping("/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public DennePoznamky ulozPoznamku(@RequestBody UlozDennePoznamkyRequest request) {
        return dennePoznamkyService.ulozPoznamku(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DennePoznamky getPoznamku(@PathVariable UUID id) {
        return dennePoznamkyService.getDennePoznamku(id);
    }

    @GetMapping("/pacient/{pacientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DennePoznamky> getZoznamPoznamokPacienta(@PathVariable UUID pacientId) {
        return dennePoznamkyService.getZoznamDennePoznamky(pacientId);
    }

    @GetMapping("/sestra/{sestraId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DennePoznamky> getZoznamPoznamokSestry(@PathVariable UUID sestraId) {
        return dennePoznamkyService.getZoznamDennePoznamkySestry(sestraId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DennePoznamky upravPoznamku(@PathVariable UUID id, @RequestBody DennePoznamky request) {
        return dennePoznamkyService.upravDennePoznamku(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazPoznamku(@PathVariable UUID id) {
        dennePoznamkyService.vymazDennePoznamku(id);
    }
}
