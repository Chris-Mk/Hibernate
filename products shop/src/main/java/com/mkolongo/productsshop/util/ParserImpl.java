package com.mkolongo.productsshop.util;

import com.google.gson.Gson;
import com.mkolongo.productsshop.util.base.Parser;
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
    public String toString(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T fromString(String str, Class<T> klass) {
        return gson.fromJson(str, klass);
    }
}
