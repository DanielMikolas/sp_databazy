package com.sp_databazy.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@RequiredArgsConstructor
public class SoapService {

    private final String SOAP_URL = "https://api.sukl.sk/Webservice2.asmx";

    public String getZoznamLiekov() {
        String soapRequest = """
                <?xml version="1.0" encoding="utf-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Body>
                        <GetZoznamLiekov xmlns="https://api.sukl.sk/" />
                    </soap:Body>
                </soap:Envelope>
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/xml; charset=utf-8");
        headers.add("SOAPAction", "https://api.sukl.sk/GetZoznamLiekov");

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(SOAP_URL, request, String.class);
        return response.getBody();
    }
}
