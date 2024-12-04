package com.sp_databazy.Service;

import com.sp_databazy.Entity.Liek;
import com.sp_databazy.Repository.LiekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LiekService {

    @Autowired
    private SoapService soapService;

    @Autowired
    private XmlParserService xmlParserService;

    @Autowired
    private LiekRepository liekRepository;

    public void synchronizujLieky() throws Exception {
        // Získaj XML z API
        String xmlResponse = soapService.getZoznamLiekov();

        // Parsuj XML na zoznam liekov
        List<Liek> lieky = xmlParserService.parseLieky(xmlResponse);

        // Ulož do databázy
        liekRepository.saveAll(lieky);
    }

    // Získanie lieku podľa SUKL kódu
    public Optional<Liek> getLiekBySuklKod(String suklKod) {
        return liekRepository.findById(suklKod);
    }

    // Získanie zoznamu všetkých liekov
    public List<Liek> getAllLieky() {
        return liekRepository.findAll();
    }
}
