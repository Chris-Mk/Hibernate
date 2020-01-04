package com.mkolongo.judgesystem.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mkolongo.judgesystem.util.base.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;

public class ParserImpl implements Parser {

    @Override
    public <T> T fromJsonString(String jsonString, Class<T> klass) {
        return new Gson().fromJson(jsonString, klass);
    }

    @Override
    public String toJsonString(Object object) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        return gson.toJson(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T fromXmlString(String xmlString, Class<T> klass) throws JAXBException {
        StringReader reader = new StringReader(xmlString);

        return (T) JAXBContext.newInstance(klass)
                .createUnmarshaller()
                .unmarshal(reader);
    }

    @Override
    public String toXmlString(Object object) throws JAXBException {
        StringWriter writer = new StringWriter();

        JAXBContext.newInstance(object.getClass())
                .createMarshaller()
                .marshal(object, writer);

        return writer.toString();
    }
}
