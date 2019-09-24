package com.mkolongo.productsshop.services;

import com.google.gson.Gson;
import com.mkolongo.productsshop.dtos.seedDtos.ProductsSeedDto;
import com.mkolongo.productsshop.dtos.viewDtos.*;
import com.mkolongo.productsshop.models.Category;
import com.mkolongo.productsshop.models.Product;
import com.mkolongo.productsshop.models.User;
import com.mkolongo.productsshop.repositories.CategoryRepository;
import com.mkolongo.productsshop.repositories.ProductRepository;
import com.mkolongo.productsshop.repositories.UserRepository;
import com.mkolongo.productsshop.services.base.ProductService;
import com.mkolongo.productsshop.util.base.FileUtil;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository,
                              UserRepository userRepository, Validator validator, ModelMapper modelMapper,
                              FileUtil fileUtil, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() == 0) {
            final String productJson = fileUtil.readFile("files/products.json");

            final ProductsSeedDto[] productsSeedDto = gson.fromJson(productJson, ProductsSeedDto[].class);

            final Set<ConstraintViolation<ProductsSeedDto[]>> violations = validator.validate(productsSeedDto);

            if (!violations.isEmpty()) {
                for (ConstraintViolation<ProductsSeedDto[]> violation : violations) {
                    System.out.println(violation.getMessage());
                }
                return;
            }

            final Product[] products = modelMapper.map(productsSeedDto, new TypeToken<Product[]>() {
            }.getType());

            final List<User> users = userRepository.findAll();
            final List<Category> categories = categoryRepository.findAll();

            for (int i = 0; i < products.length; i++) {
                Product currentProduct = products[i];
                final User randomSeller = getRandomUser(users);

                currentProduct.setSellerId(randomSeller);

                if (i % 5 == 0) {
                    currentProduct.setBuyerId(null);
                } else {
                    final User randomBuyer = getRandomUser(users);
                    currentProduct.setBuyerId(randomBuyer);
                }

                final Set<Category> randomCategory = getRandomCategories(categories, currentProduct);
                currentProduct.setCategories(randomCategory);
            }

            productRepository.saveAll(Arrays.asList(products));
        }
    }

    @Override
    public String getProductsWithPriceInRange(BigDecimal lowerBound, BigDecimal upperBound) {
        final List<Product> products = productRepository
                .findProductsByPriceBetweenAndBuyerIdIsNullOrderByPrice(lowerBound, upperBound);

        Converter<Product, String> getSellerFullName = new AbstractConverter<>() {
            @Override
            protected String convert(Product source) {
                final String firstName = source.getSellerId().getFirstName();
                final String lastName = source.getSellerId().getLastName();

                return firstName + " " + lastName;
            }
        };

        modelMapper.createTypeMap(Product.class, ProductsInRangeDto.class)
                .addMappings(mapping ->
                        mapping.using(getSellerFullName).map(
                                source -> source,
                                ProductsInRangeDto::setSellerFullName)
                );

        final List<ProductsInRangeDto> productInRangeDto =
                modelMapper.map(products, new TypeToken<List<ProductsInRangeDto>>() {
                }.getType());

        return gson.toJson(productInRangeDto);
    }

    @Override
    public String getSoldProducts() {
        final String[] json = {""};

        modelMapper.createTypeMap(Product.class, ProductViewDto.class)
                .addMappings(mapping -> {

                    mapping.map(source -> source.getBuyerId().getFirstName(),
                            (destination, value) -> destination.setBuyerFirstName(value == null ? "" : (String) value));

                    mapping.map(source -> source.getBuyerId().getLastName(),
                            (destination, value) -> destination.setBuyerLastName((String) value));
                });

        productRepository.findProductsByBuyerIdNotNull()
                .stream()
                .collect(Collectors.groupingBy(Product::getSellerId))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    int sort = e1.getKey().getLastName().compareTo(e2.getKey().getLastName());

                    if (sort == 0) {
                        if (e1.getKey().getFirstName() != null && e2.getKey().getFirstName() != null) {
                            sort = e1.getKey().getFirstName().compareTo(e2.getKey().getFirstName());
                        }
                    }
                    return sort;
                })
                .forEach(e -> {
                    List<ProductViewDto> productViewDto =
                            modelMapper.map(e.getValue(), new TypeToken<List<ProductViewDto>>() {
                            }.getType());

                    SoldProductsDto soldProductsDto = modelMapper.map(e.getKey(), SoldProductsDto.class);
                    soldProductsDto.setSoldProducts(productViewDto);

                    json[0] += gson.toJson(soldProductsDto);
                });
        return json[0];
    }

    @Override
    public String getUsersWithSoldProducts() {
        modelMapper.createTypeMap(Product.class, ProductViewDto.class)
                .addMappings(mapping -> {
                    mapping.skip(ProductViewDto::setBuyerFirstName);
                    mapping.skip(ProductViewDto::setBuyerLastName);
                });

        String[] json = {""};

        List<UserDto> userDtos = new ArrayList<>();

        productRepository.findProductsByBuyerIdNotNull()
                .stream()
                .collect(Collectors.groupingBy(Product::getSellerId))
                .entrySet()
                .stream()
                .sorted((u1, u2) -> {
                    int sort = Integer.compare(u2.getValue().size(), u1.getValue().size());

                    if (sort == 0) {
                        sort = u1.getKey().getLastName().compareTo(u2.getKey().getLastName());
                    }
                    return sort;
                })
                .forEach(e -> {
                    List<ProductViewDto> productViewDto =
                            modelMapper.map(e.getValue(), new TypeToken<List<ProductViewDto>>() {}.getType());

                    ProductInfoDto productInfoDto = new ProductInfoDto();
                    productInfoDto.setCount(productViewDto.size());
                    productInfoDto.setProducts(productViewDto);

                    UserDto userDto = modelMapper.map(e.getKey(), UserDto.class);
                    userDto.setSoldProducts(productInfoDto);

                    userDtos.add(userDto);
                });

        final UsersAndProductsDto usersAndProductsDto = new UsersAndProductsDto();
        usersAndProductsDto.setUsersCount(userDtos.size());
        usersAndProductsDto.setUsers(userDtos);

        json[0] = gson.toJson(usersAndProductsDto);

        return json[0];
    }

    private Set<Category> getRandomCategories(List<Category> categories, Product currentProduct) {
        Set<Category> randomCategories = new LinkedHashSet<>();

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(categories);

            final Category currentCategory = categories.get(0);

            currentCategory.setProducts(Set.of(currentProduct));
            randomCategories.add(currentCategory);
        }
        return randomCategories;
    }

    private User getRandomUser(List<User> users) {
//        users.get(random.nextInt(users.size() - 1) + 1);

        Collections.shuffle(users);
        return users.get(0);
    }
}
