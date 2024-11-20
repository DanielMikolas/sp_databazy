package com.sp_databazy.Service;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Repository.DoktorRepository;
import com.sp_databazy.Repository.PouzivatelRepository;
import com.sp_databazy.Request.UlozDoktoraRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoktorService {

    private final DoktorRepository doktorRepository;
    private final PouzivatelRepository pouzivatelRepository;

    // Vytvorenie doktora
    public Doktor ulozDoktora(UlozDoktoraRequest ulozDoktorRequest) {
        Doktor doktor = new Doktor();
        doktor.setOddelenie(ulozDoktorRequest.getOddelenie());
        doktor.setSpecializacia(ulozDoktorRequest.getSpecializacia());

        // Načítanie Pouzivatela podľa jeho ID
        Pouzivatel pouzivatel = pouzivatelRepository.findById(ulozDoktorRequest.getPouzivatelId())
                .orElseThrow(() -> new IllegalArgumentException("Používateľ s týmto ID neexistuje"));

        doktor.setPouzivatelId(pouzivatel);  // Priradenie Pouzivatela do entity Sestra
        return doktorRepository.save(doktor);
    }

    // Načítanie doktora podľa ID
    public Doktor getDoktor(UUID id) {
        return doktorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));
    }

    // Načítanie zoznamu všetkých doktorov
    public List<Doktor> getZoznamDoktorov() {
        return doktorRepository.findAll();
    }

    // Úprava informácií o doktorovi
    public Doktor upravDoktora(UUID id, UlozDoktoraRequest ulozDoktorRequest) {
        Doktor doktor = doktorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));

        doktor.setSpecializacia(ulozDoktorRequest.getSpecializacia());
        doktor.setOddelenie(ulozDoktorRequest.getOddelenie());

        return doktorRepository.save(doktor);
    }

    // Vymazanie doktora
    public void vymazDoktora(UUID id) {
        if (!doktorRepository.existsById(id)) {
            throw new NoSuchElementException("Doktor s týmto ID neexistuje");
        }
        doktorRepository.deleteById(id);
    }
}
