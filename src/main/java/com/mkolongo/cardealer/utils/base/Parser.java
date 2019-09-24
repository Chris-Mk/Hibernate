package com.mkolongo.cardealer.utils.base;

public interface Parser {

    String toJsonString(Object object);

    String toXmlString(Object object);

    <T> T toObject(String str, Class<T> klass);
}
