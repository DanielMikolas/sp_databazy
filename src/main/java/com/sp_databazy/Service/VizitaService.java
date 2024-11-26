package com.sp_databazy.Service;

import com.sp_databazy.Entity.*;
import com.sp_databazy.Repository.DoktorRepository;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Repository.SestraRepository;
import com.sp_databazy.Repository.VizitaRepository;
import com.sp_databazy.Request.UlozVizituRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VizitaService {

    private final VizitaRepository vizitaRepository;
    private final PacientRepository pacientRepository;
    private final DoktorRepository doktorRepository;
    private final SestraRepository sestraRepository;

    // Uloženie novej vizity
    public Vizita ulozVizitu(UlozVizituRequest ulozVizituRequest) {
        // Načítanie entít podľa UUID
        Optional<Pacient> pacient = pacientRepository.findById(ulozVizituRequest.getPacientId());
        Optional<Doktor> doktor = doktorRepository.findById(ulozVizituRequest.getDoktorId());
        Optional<Sestra> sestra = sestraRepository.findById(ulozVizituRequest.getSestraId());

        // Overenie, že všetky entity existujú
        if (pacient.isEmpty() || doktor.isEmpty() || sestra.isEmpty()) {
            throw new IllegalArgumentException("Pacient, doktor alebo sestra neexistujú.");
        }

        // Vytvorenie novej vizity
        Vizita vizita = new Vizita();
        vizita.setPacient(pacient.get()); // Nastavíme objekt Pacient
        vizita.setDoktor(doktor.get());   // Nastavíme objekt Doktor
        vizita.setSestra(sestra.get());   // Nastavíme objekt Sestra
        vizita.setPoznamky(ulozVizituRequest.getPoznamky());
        vizita.setDatum(ulozVizituRequest.getDatum());

        // Uloženie a vrátenie vizity
        return vizitaRepository.save(vizita);
    }

    // Získanie vizity podľa ID
    public Optional<Vizita> getVizitaById(UUID id) {
        return vizitaRepository.findById(id);
    }

    // Získanie zoznamu všetkých vizít
    public List<Vizita> getAllVizity() {
        return vizitaRepository.findAll();
    }

    // Vymazanie vizity podľa ID
    public boolean deleteVizita(UUID id) {
        if (vizitaRepository.existsById(id)) {
            vizitaRepository.deleteById(id);
            return true;
        }
        return false;  // Ak vizita neexistuje, vrátime false
    }


    // Úprava informácií o vizite (poznamky a datum)
    public Vizita upravVizitu(UUID id, UlozVizituRequest ulozVizituRequest) {

        Vizita vizita = vizitaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vizita s týmto ID neexistuje"));

        Pacient pacient = pacientRepository.findById(ulozVizituRequest.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s ID '" + ulozVizituRequest.getPacientId() + "' neexistuje."));

        Sestra sestra = sestraRepository.findById(ulozVizituRequest.getSestraId())
                .orElseThrow(() -> new NoSuchElementException("Sestra s ID '" + ulozVizituRequest.getSestraId() + "' neexistuje."));

        Doktor doktor = doktorRepository.findById(ulozVizituRequest.getDoktorId())
                .orElseThrow(() -> new NoSuchElementException("Doktor s ID '" + ulozVizituRequest.getDoktorId() + "' neexistuje."));

        vizita.setPacient(pacient);
        vizita.setSestra(sestra);
        vizita.setDoktor(doktor);


        vizita.setPoznamky(ulozVizituRequest.getPoznamky());
        vizita.setDatum(ulozVizituRequest.getDatum());

        return vizitaRepository.save(vizita);
    }
}
