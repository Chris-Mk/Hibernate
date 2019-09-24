package com.mkolongo.productsshop.repositories;

import com.mkolongo.productsshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findProductsByPriceBetweenAndBuyerIdIsNullOrderByPrice(BigDecimal lowerBound, BigDecimal upperBound);

    List<Product> findProductsByBuyerIdNotNull();
}
