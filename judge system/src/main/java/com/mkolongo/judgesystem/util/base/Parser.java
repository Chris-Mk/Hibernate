package com.mkolongo.judgesystem.util.base;

import javax.xml.bind.JAXBException;

public interface Parser {

    <T> T fromJsonString(String jsonString, Class<T> klass);

    String toJsonString(Object object);

    <T> T fromXmlString(String xmlString, Class<T> klass) throws JAXBException;

    String toXmlString(Object object) throws JAXBException;
}
