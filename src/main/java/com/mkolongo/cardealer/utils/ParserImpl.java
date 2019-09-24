package com.mkolongo.cardealer.utils;

import com.google.gson.Gson;
import com.mkolongo.cardealer.utils.base.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public String toXmlString(Object object) {
        return null;
    }

    @Override
    public <T> T toObject(String str, Class<T> klass) {
        return gson.fromJson(str, klass);
    }
}
