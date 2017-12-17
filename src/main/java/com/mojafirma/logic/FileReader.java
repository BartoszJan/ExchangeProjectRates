package com.mojafirma.logic;

import com.mojafirma.model.RatesTableModelXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class FileReader {

    public void readFile(File file) {

        RatesTableModelXML ratesTable = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RatesTableModelXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ratesTable = (RatesTableModelXML) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        DataWriter dataWriter = new DataWriter();
        dataWriter.writeData(ratesTable);
    }
}
