package com.sp_databazy.Service;

import com.sp_databazy.Entity.Liek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.InputSource;

@Service
@RequiredArgsConstructor
public class XmlParserService {

    public List<Liek> parseLieky(String xmlResponse) throws Exception {
        List<Liek> lieky = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

        NodeList liekyNodes = doc.getElementsByTagName("zoznam_liekov");
        for (int i = 0; i < liekyNodes.getLength(); i++) {
            Element liekElement = (Element) liekyNodes.item(i);

            Liek liek = new Liek();
            liek.setSuklKod(getElementValue(liekElement, "sukl_kod"));
            liek.setNazov(getElementValue(liekElement, "nazov"));
            liek.setDoplnok(getElementValue(liekElement, "doplnok"));
            liek.setKodDrz(getElementValue(liekElement, "kod_drz"));
            liek.setKodStatu(getElementValue(liekElement, "kod_statu"));
            liek.setAtcKod(getElementValue(liekElement, "atc_kod"));
            liek.setAtcNazovSk(getElementValue(liekElement, "atc_nazov_sk"));
            liek.setIsFlag(getElementValue(liekElement, "is"));
            liek.setRegCislo(getElementValue(liekElement, "reg_cislo"));
            liek.setExpiracia(getElementValue(liekElement, "expiracia"));
            liek.setVydaj(getElementValue(liekElement, "vydaj"));
            liek.setTypReg(getElementValue(liekElement, "typ_reg"));
            liek.setDatumReg(getElementValue(liekElement, "datum_reg"));
            liek.setPlatnost(getElementValue(liekElement, "platnost"));
            liek.setBp(getElementValue(liekElement, "bp"));

            lieky.add(liek);
        }

        return lieky;
    }

    private String getElementValue(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        return nodes.getLength() > 0 ? nodes.item(0).getTextContent() : null;
    }
}
