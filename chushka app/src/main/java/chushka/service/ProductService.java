package chushka.service;

import chushka.domain.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void persist(Product product);

    Product getProductByName(String id);
}
