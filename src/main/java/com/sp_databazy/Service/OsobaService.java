package com.sp_databazy.Service;

import com.sp_databazy.Entity.Osoba;
import com.sp_databazy.Repository.OsobaRepository;
import com.sp_databazy.Request.UlozOsobaRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OsobaService {

    private final OsobaRepository osobaRepository;

    public void ulozOsobu(UlozOsobaRequest ulozOsobaRequest) {
        if(osobaRepository.existsByRodneCislo(ulozOsobaRequest.getRodneCislo())){
            throw new NoSuchElementException("Osoba s tymto rodnym cislom uz existuje");
        }

        Osoba osoba = new Osoba();
        osoba.setMeno(ulozOsobaRequest.getMeno());
        osoba.setPriezvisko((ulozOsobaRequest.getPriezvisko()));
        osoba.setDatumNarodenia(ulozOsobaRequest.getDatumNarodenia());
        osoba.setRodneCislo(ulozOsobaRequest.getRodneCislo());
        osoba.setAdresa(ulozOsobaRequest.getAdresa());
        osoba.setTyp(ulozOsobaRequest.getTyp());

        osobaRepository.save(osoba);
    }

    public Osoba getOsoba(String rodneCislo) {
//        if(!osobaRepository.existsByRodneCislo(rodneCislo)){
//            throw new NoSuchElementException("Osoba s tymto rodnym cislo neexistuje");
//        }
//        return osobaRepository.findByRodneCislo(rodneCislo);

        return osobaRepository.findByRodneCislo(rodneCislo)
                .orElseThrow(() -> new NoSuchElementException("Osoba s týmto rodným číslom neexistuje"));
    }

    public List<Osoba> getZoznam() {
        return osobaRepository.findAll();
    }

    public void vymazOsobu(UUID id) {
        if(!osobaRepository.existsById(id)){
            throw new NoSuchElementException("Osoba s danym id neexistuje");
        }
        osobaRepository.deleteById(id);
    }

    public void upravOsobu(UUID id, UlozOsobaRequest ulozOsobaRequest){
//        if(!osobaRepository.existsById(id)){
//            throw new NoSuchElementException("Osoba s danym id neexistuje");
//        }

        Osoba osoba = osobaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Osoba s daným ID neexistuje"));

 //       Osoba osoba = osobaRepository.findById(id).get();
        osoba.setMeno(ulozOsobaRequest.getMeno());
        osoba.setPriezvisko((ulozOsobaRequest.getPriezvisko()));
        osoba.setDatumNarodenia(ulozOsobaRequest.getDatumNarodenia());
        osoba.setRodneCislo(ulozOsobaRequest.getRodneCislo());
        osoba.setAdresa(ulozOsobaRequest.getAdresa());
        osoba.setTyp(ulozOsobaRequest.getTyp());

        osobaRepository.save(osoba);
    }
}
