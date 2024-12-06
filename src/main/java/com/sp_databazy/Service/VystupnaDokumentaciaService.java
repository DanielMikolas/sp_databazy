package com.sp_databazy.Service;

import com.sp_databazy.Entity.*;
import com.sp_databazy.Repository.*;
import com.sp_databazy.Request.UlozVystupnuDokRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VystupnaDokumentaciaService {

    @Autowired
    private VystupnaDokRepository repository;

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private DiagnozaRepository diagnozaRepository;

    @Autowired
    private DoktorRepository doktorRepository;

    @Autowired
    private SestraRepository sestraRepository;

    @Autowired
    private PrijemRepository prijemRepository;

    public VystupnaDokumentacia saveVystupnaDokumentacia(UlozVystupnuDokRequest request) {
        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new RuntimeException("Pacient not found"));
        Diagnoza diagnoza = diagnozaRepository.findById(request.getDiagnozaKod())
                .orElseThrow(() -> new RuntimeException("Diagnoza not found"));
        Doktor doktor = doktorRepository.findById(request.getOsetrujuciLekarId())
                .orElseThrow(() -> new RuntimeException("Doktor not found"));
        Sestra sestra = sestraRepository.findById(request.getVystupSestraId())
                .orElseThrow(() -> new RuntimeException("Sestra not found"));
        Prijem prijem = prijemRepository.findById(request.getPrijemId())
                .orElseThrow(() -> new RuntimeException("Prijem not found"));

        VystupnaDokumentacia dokumentacia = new VystupnaDokumentacia();
        dokumentacia.setPacient(pacient);
        dokumentacia.setDatumPrepustenia(request.getDatumPrepustenia());
        dokumentacia.setDiagnoza(diagnoza);
        dokumentacia.setStavPriPrepusteni(request.getStavPriPrepusteni());
        dokumentacia.setOdporucenia(request.getOdporucenia());
        dokumentacia.setLiecbaPoPrepusteni(request.getLiecbaPoPrepusteni());
        dokumentacia.setOsetrujuciLekar(doktor);
        dokumentacia.setVystupSestra(sestra);
        dokumentacia.setPrijem(prijem);
        dokumentacia.setVypisOPriebehuLiečby(request.getVypisOPriebehuLiečby());
        dokumentacia.setPoslednaZdravotnaObservacia(request.getPoslednaZdravotnaObservacia());
        dokumentacia.setPoznamky(request.getPoznamky());

        return repository.save(dokumentacia);
    }

    public List<VystupnaDokumentacia> findByPacientId(UUID pacientId) {
        return repository.findByPacientId(pacientId);
    }

    public List<VystupnaDokumentacia> findByOsetrujuciLekarId(UUID osetrujuciLekarId) {
        return repository.findByOsetrujuciLekarId(osetrujuciLekarId);
    }

    public List<VystupnaDokumentacia> findByVystupSestraId(UUID vystupSestraId) {
        return repository.findByVystupSestraId(vystupSestraId);
    }

    public List<VystupnaDokumentacia> findByPrijemId(UUID prijemId) {
        return repository.findByPrijemId(prijemId);
    }
    // Získanie všetkých dokumentácií
    public List<VystupnaDokumentacia> findAll() {
        return repository.findAll();
    }

    // Aktualizácia dokumentácie
    public VystupnaDokumentacia updateVystupnaDokumentacia(UUID id, UlozVystupnuDokRequest request) {
        VystupnaDokumentacia dokumentacia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokumentácia not found"));

        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new RuntimeException("Pacient not found"));
        Diagnoza diagnoza = diagnozaRepository.findById(request.getDiagnozaKod())
                .orElseThrow(() -> new RuntimeException("Diagnoza not found"));
        Doktor doktor = doktorRepository.findById(request.getOsetrujuciLekarId())
                .orElseThrow(() -> new RuntimeException("Doktor not found"));
        Sestra sestra = sestraRepository.findById(request.getVystupSestraId())
                .orElseThrow(() -> new RuntimeException("Sestra not found"));
        Prijem prijem = prijemRepository.findById(request.getPrijemId())
                .orElseThrow(() -> new RuntimeException("Prijem not found"));

        dokumentacia.setPacient(pacient);
        dokumentacia.setDatumPrepustenia(request.getDatumPrepustenia());
        dokumentacia.setDiagnoza(diagnoza);
        dokumentacia.setStavPriPrepusteni(request.getStavPriPrepusteni());
        dokumentacia.setOdporucenia(request.getOdporucenia());
        dokumentacia.setLiecbaPoPrepusteni(request.getLiecbaPoPrepusteni());
        dokumentacia.setOsetrujuciLekar(doktor);
        dokumentacia.setVystupSestra(sestra);
        dokumentacia.setPrijem(prijem);
        dokumentacia.setVypisOPriebehuLiečby(request.getVypisOPriebehuLiečby());
        dokumentacia.setPoslednaZdravotnaObservacia(request.getPoslednaZdravotnaObservacia());
        dokumentacia.setPoznamky(request.getPoznamky());

        return repository.save(dokumentacia);
    }

    // Vymazanie dokumentácie podľa ID
    public void deleteVystupnaDokumentacia(UUID id) {
        VystupnaDokumentacia dokumentacia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokumentácia not found"));
        repository.delete(dokumentacia);
    }
}
