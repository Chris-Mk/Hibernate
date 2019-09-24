package com.mkolongo.productsshop.services.base;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {

    void seedProducts() throws IOException;

    String getProductsWithPriceInRange(BigDecimal lowerBound, BigDecimal upperBound);

    String getSoldProducts();

    String getUsersWithSoldProducts();
}
