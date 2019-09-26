package com.mkolongo.cardealer.utils;

import com.google.gson.Gson;
import com.mkolongo.cardealer.utils.base.FileUtil;
import com.mkolongo.cardealer.utils.base.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
public class ParserImpl implements Parser {
    private final Gson gson;

    @Autowired
    public ParserImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJsonString(Object object) {
        return gson.toJson(object);
    }

    @Override
    public void toXmlString(Object object) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(object, System.out);
    }

    @Override
    public <T> T fromJsonString(String jsonString, Class<T> klass) {
        return gson.fromJson(jsonString, klass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T fromXmlString(String xmlString, Class<T> klass) throws JAXBException {
        StringReader reader = new StringReader(xmlString);

        JAXBContext jaxbContext = JAXBContext.newInstance(klass);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (T) unmarshaller.unmarshal(reader);
    }
}
