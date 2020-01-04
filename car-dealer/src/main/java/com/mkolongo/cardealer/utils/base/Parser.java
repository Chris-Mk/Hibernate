package com.mkolongo.cardealer.utils.base;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Parser {

    String toJsonString(Object object);

    void toXmlString(Object object) throws JAXBException;

    <T> T fromJsonString(String jsonString, Class<T> klass);

    <T> T fromXmlString(String xmlString, Class<T> klass) throws JAXBException, IOException;
}
