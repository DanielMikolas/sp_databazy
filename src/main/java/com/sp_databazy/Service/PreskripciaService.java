package com.sp_databazy.Service;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Liek;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Preskripcia;
import com.sp_databazy.Repository.DoktorRepository;
import com.sp_databazy.Repository.LiekRepository;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Repository.PreskripciaRepository;
import com.sp_databazy.Request.UlozPreskripciaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PreskripciaService {

    private final PreskripciaRepository preskripciaRepository;
    private final LiekRepository liekRepository;
    private final PacientRepository pacientRepository;
    private final DoktorRepository doktorRepository;

    // Získanie zoznamu všetkých predpisov
    public List<Preskripcia> getAllPreskripcie() {
        return preskripciaRepository.findAll();
    }

    // Získanie predpisu podľa ID
    public Optional<Preskripcia> getPreskripciaById(UUID id) {
        return preskripciaRepository.findById(id);
    }

    // Získanie predpisov podľa ID pacienta
    public List<Preskripcia> getPreskripcieByPacientId(UUID pacientId) {
        Pacient pacient = pacientRepository.findById(pacientId)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));
        return preskripciaRepository.findByPacient(pacient);
    }

    // Vytvorenie alebo uloženie predpisu
    public Preskripcia createPreskripcia(UlozPreskripciaRequest request) {
        // Logovanie pred získaním lieku
        System.out.println("Hľadám liek s SUKL kódom: " + request.getSuklKod());

        Liek liek = liekRepository.findBySuklKod(request.getSuklKod())
                .orElseThrow(() -> new NoSuchElementException("Liek s daným SUKL kódom neexistuje: " + request.getSuklKod()));

        // Logovanie po získaní lieku
        System.out.println("Liek nájdený: " + liek.getNazov());

        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        Doktor doktor = doktorRepository.findById(request.getDoktorId())
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));

        // Vytvorenie a uloženie predpisu
        Preskripcia preskripcia = new Preskripcia();
        preskripcia.setLiek(liek);
        preskripcia.setPacient(pacient);
        preskripcia.setDoktor(doktor);
        preskripcia.setDatumPredpisu(request.getDatumPredpisu());
        preskripcia.setDavkovanie(request.getDavkovanie());

        return preskripciaRepository.save(preskripcia);
    }

    // Aktualizácia dávkovania
    public Preskripcia updateDavkovanie(UUID id, UlozPreskripciaRequest ulozPreskripciaRequest) {
        Preskripcia preskripcia = preskripciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Predpis s daným ID neexistuje"));
        Liek liek = liekRepository.findBySuklKod(ulozPreskripciaRequest.getSuklKod())
                .orElseThrow(() -> new NoSuchElementException("Liek s daným SUKL kódom neexistuje: " + ulozPreskripciaRequest.getSuklKod()));

        // Logovanie po získaní lieku
        System.out.println("Liek nájdený: " + liek.getNazov());

        Pacient pacient = pacientRepository.findById(ulozPreskripciaRequest.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        Doktor doktor = doktorRepository.findById(ulozPreskripciaRequest.getDoktorId())
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));

        preskripcia.setLiek(liek);
        preskripcia.setPacient(pacient);
        preskripcia.setDoktor(doktor);

        preskripcia.setDavkovanie(ulozPreskripciaRequest.getDavkovanie());
        return preskripciaRepository.save(preskripcia);
    }

    // Vymazanie predpisu podľa ID
    public void deletePreskripciaById(UUID id) {
        if (!preskripciaRepository.existsById(id)) {
            throw new NoSuchElementException("Predpis s daným ID neexistuje");
        }
        preskripciaRepository.deleteById(id);
    }
}
