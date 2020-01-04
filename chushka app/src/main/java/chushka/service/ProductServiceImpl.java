package chushka.service;

import chushka.domain.entities.Product;
import chushka.repository.ProductRepository;

import javax.inject.Inject;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void persist(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product getProductByName(String name) {
        return this.productRepository.findByName(name);
    }
}
