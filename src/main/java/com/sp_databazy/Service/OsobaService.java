package com.sp_databazy.Service;

import com.sp_databazy.Entity.Osoba;
import com.sp_databazy.Repository.OsobaRepository;
import com.sp_databazy.Request.UlozOsobaRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
        SetOsoba(ulozOsobaRequest, osoba);
    }

    private void SetOsoba(UlozOsobaRequest ulozOsobaRequest, Osoba osoba) {
        osoba.setMeno(ulozOsobaRequest.getMeno());
        osoba.setPriezvisko((ulozOsobaRequest.getPriezvisko()));
        osoba.setDatumNarodenia(ulozOsobaRequest.getDatumNarodenia());
        osoba.setRodneCislo(ulozOsobaRequest.getRodneCislo());
        osoba.setAdresa(ulozOsobaRequest.getAdresa());
        osoba.setTyp(ulozOsobaRequest.getTyp());

        osobaRepository.save(osoba);
    }

    public Osoba getOsoba(String rodneCislo) {


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


        Osoba osoba = osobaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Osoba s daným ID neexistuje"));


        SetOsoba(ulozOsobaRequest, osoba);
    }
}
