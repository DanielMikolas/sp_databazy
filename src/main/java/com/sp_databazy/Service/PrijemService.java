package com.sp_databazy.Service;

import com.sp_databazy.Entity.*;
import com.sp_databazy.Repository.*;
import com.sp_databazy.Request.UlozPrijemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrijemService {

    private final PrijemRepository prijemRepository;
    private final DiagnozaRepository diagnozaRepository;
    private final PacientRepository pacientRepository;
    private final DoktorRepository doktorRepository;
    private final OddelenieRepository oddelenieRepository;
    private final SestraRepository sestraRepository;

    public Prijem ulozPrijem(UlozPrijemRequest request) {
        // Nájdenie všetkých referenčných entít
        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new IllegalArgumentException("Pacient not found"));

        Diagnoza diagnoza = diagnozaRepository.findById(request.getDiagnozaKod())
                .orElseThrow(() -> new IllegalArgumentException("Diagnoza not found"));

        Doktor osetrujuciLekar = doktorRepository.findById(request.getOsetrujuciLekarId())
                .orElseThrow(() -> new IllegalArgumentException("Doktor not found"));

        Oddelenie oddelenie = oddelenieRepository.findById(request.getOddelenieId())
                .orElseThrow(() -> new IllegalArgumentException("Oddelenie not found"));

        Sestra prijemSestra = sestraRepository.findById(request.getPrijemSestraId())
                .orElseThrow(() -> new IllegalArgumentException("Sestra not found"));

        // Vytvorenie objektu Prijem
        Prijem prijem = new Prijem();
        return setPrijem(request, pacient, diagnoza, osetrujuciLekar, oddelenie, prijemSestra, prijem);
    }

    private Prijem setPrijem(UlozPrijemRequest request, Pacient pacient, Diagnoza diagnoza, Doktor osetrujuciLekar, Oddelenie oddelenie, Sestra prijemSestra, Prijem prijem) {
        prijem.setPacientId(pacient);
        prijem.setDatumPrijmu(request.getDatumPrijmu());
        prijem.setDovodPrijmu(request.getDovodPrijmu());
        prijem.setDiagnoza(diagnoza);
        prijem.setStavPriPrijme(request.getStavPriPrijme());
        prijem.setOsetrujuciLekarId(osetrujuciLekar);
        prijem.setOddelenieId(oddelenie);
        prijem.setPrijemSestraId(prijemSestra);
        prijem.setZaznamOPrijme(request.getZaznamOPrijme());
        prijem.setPoznamky(request.getPoznamky());
        prijem.setOsVeciPac(request.getOsVeciPac());

        return prijemRepository.save(prijem);
    }

    public List<Prijem> vsetkyPrijmy() {
        return prijemRepository.findAll();
    }

    public Prijem najdiPrijemPodlaId(UUID id) {
        return prijemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prijem not found"));
    }

    public List<Prijem> najdiPrijemPodlaPacienta(UUID pacientId) {
        return prijemRepository.findByPacientId_Id(pacientId);
    }

    public List<Prijem> najdiPrijemPodlaLekara(UUID lekarId) {
        return prijemRepository.findByOsetrujuciLekarId_Id(lekarId);
    }

    public List<Prijem> najdiPrijemPodlaSestry(UUID sestraId) {
        return prijemRepository.findByPrijemSestraId_Id(sestraId);
    }

    public List<Prijem> najdiPrijemPodlaOddelenia(UUID oddelenieId) {
        return prijemRepository.findByOddelenieId_Id(oddelenieId);
    }

    public Prijem upravPrijem(UUID id, UlozPrijemRequest request) {
        Prijem prijem = najdiPrijemPodlaId(id);

        // Aktualizácia referenčných entít
        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new IllegalArgumentException("Pacient not found"));

        Diagnoza diagnoza = diagnozaRepository.findById(request.getDiagnozaKod())
                .orElseThrow(() -> new IllegalArgumentException("Diagnoza not found"));

        Doktor osetrujuciLekar = doktorRepository.findById(request.getOsetrujuciLekarId())
                .orElseThrow(() -> new IllegalArgumentException("Doktor not found"));

        Oddelenie oddelenie = oddelenieRepository.findById(request.getOddelenieId())
                .orElseThrow(() -> new IllegalArgumentException("Oddelenie not found"));

        Sestra prijemSestra = sestraRepository.findById(request.getPrijemSestraId())
                .orElseThrow(() -> new IllegalArgumentException("Sestra not found"));

        // Aktualizácia objektu Prijem
        return setPrijem(request, pacient, diagnoza, osetrujuciLekar, oddelenie, prijemSestra, prijem);
    }

    public void vymazPrijem(UUID id) {
        prijemRepository.deleteById(id);
    }
}
