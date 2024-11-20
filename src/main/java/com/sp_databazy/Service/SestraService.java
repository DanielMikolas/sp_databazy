package com.sp_databazy.Service;

import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Repository.PouzivatelRepository;
import com.sp_databazy.Repository.SestraRepository;
import com.sp_databazy.Request.UlozSestruRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SestraService {

    private final SestraRepository sestraRepository;
    private final PouzivatelRepository pouzivatelRepository;

    // Vytvorenie sestry
    public Sestra ulozSestru(UlozSestruRequest ulozSestraRequest) {
        Sestra sestra = new Sestra();
        sestra.setOddelenie(ulozSestraRequest.getOddelenie());

        // Načítanie Pouzivatela podľa jeho ID
        Pouzivatel pouzivatel = pouzivatelRepository.findById(ulozSestraRequest.getPouzivatelId())
                .orElseThrow(() -> new IllegalArgumentException("Používateľ s týmto ID neexistuje"));

        sestra.setPouzivatelId(pouzivatel);  // Priradenie Pouzivatela do entity Sestra
        return sestraRepository.save(sestra);
    }

    // Načítanie sestry podľa ID
    public Sestra getSestra(UUID id) {
        return sestraRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sestra s týmto ID neexistuje"));
    }

    // Načítanie zoznamu všetkých sestier
    public List<Sestra> getZoznamSestri() {
        return sestraRepository.findAll();
    }

    // Úprava informácií o sestre
    public Sestra upravSestru(UUID id, UlozSestruRequest ulozSestraRequest) {
        Sestra sestra = sestraRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sestra s týmto ID neexistuje"));

        sestra.setOddelenie(ulozSestraRequest.getOddelenie());

        return sestraRepository.save(sestra);
    }

    // Vymazanie sestry
    public void vymazSestru(UUID id) {
        if (!sestraRepository.existsById(id)) {
            throw new NoSuchElementException("Sestra s týmto ID neexistuje");
        }
        sestraRepository.deleteById(id);
    }
}
