package com.sp_databazy.Service;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Oddelenie;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Repository.DoktorRepository;
import com.sp_databazy.Repository.OddelenieRepository;
import com.sp_databazy.Request.UlozOddelenieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OddelenieService {


    private final OddelenieRepository oddelenieRepository;
    private final DoktorRepository doktorRepository;


    public Oddelenie ulozOddelenie(UlozOddelenieRequest request) {
        // Kontrola, či oddelenie s daným názvom už existuje
        Optional<Oddelenie> existujuceOddelenie = oddelenieRepository.findByNazov(request.getNazov());
        if (existujuceOddelenie.isPresent()) {
            throw new IllegalArgumentException("Oddelenie s názvom '" + request.getNazov() + "' už existuje.");
        }

        Doktor doktor = doktorRepository.findById(request.getVeduciDoktorId())
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));

        // Vytvorenie novej inštancie Oddelenie
        Oddelenie oddelenie = new Oddelenie();
        oddelenie.setNazov(request.getNazov());
        oddelenie.setVeduciDoktorId(doktor);

        return oddelenieRepository.save(oddelenie);
    }

    public List<Oddelenie> getVsetkyOddelenia() {
        return oddelenieRepository.findAll();
    }

    public Oddelenie getOddelenieById(UUID id) {
        return oddelenieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Oddelenie s ID '" + id + "' nebolo nájdené.")
        );
    }

    public Oddelenie getOddelenieByVeduciDoktorId(UUID veduciDoktorId) {
        // Získanie oddelenia na základe ID vedúceho doktora
//        return oddelenieRepository.findByVeduciDoktorId(veduciDoktorId).orElseThrow(
//                () -> new IllegalArgumentException("Oddelenie s vedúcim doktorom s ID '" + veduciDoktorId + "' nebolo nájdené.")
//        );
        Doktor doktor = doktorRepository.findById(veduciDoktorId)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        return oddelenieRepository.findByVeduciDoktorId(doktor);
    }

    public Oddelenie upravOddelenie(UUID id, UlozOddelenieRequest request) {
        // Zistenie existencie oddelenia
        Oddelenie existujuceOddelenie = oddelenieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Oddelenie s ID '" + id + "' nebolo nájdené.")
        );

        // Overenie, či nový názov už neexistuje (pre iné oddelenie)
        Optional<Oddelenie> oddelenieSNovymNazvom = oddelenieRepository.findByNazov(request.getNazov());
        if (oddelenieSNovymNazvom.isPresent() && !oddelenieSNovymNazvom.get().getId().equals(id)) {
            throw new IllegalArgumentException("Oddelenie s názvom '" + request.getNazov() + "' už existuje.");
        }

        Doktor doktor = doktorRepository.findById(request.getVeduciDoktorId())
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));

        // Aktualizácia existujúceho oddelenia
        existujuceOddelenie.setNazov(request.getNazov());
        existujuceOddelenie.setVeduciDoktorId(doktor);

        return oddelenieRepository.save(existujuceOddelenie);
    }

    public void vymazOddelenie(UUID id) {
        if (!oddelenieRepository.existsById(id)) {
            throw new IllegalArgumentException("Oddelenie s ID '" + id + "' nebolo nájdené.");
        }
        oddelenieRepository.deleteById(id);
    }
}
