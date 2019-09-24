package com.mkolongo.productsshop.util.base;

public interface Parser {

    String toString(Object object);

    <T> T fromString(String str, Class<T> klass);
}
