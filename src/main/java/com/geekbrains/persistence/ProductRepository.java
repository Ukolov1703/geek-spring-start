package com.geekbrains.persistence;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepository {


    private static final ProductRepository INSTANCE = new ProductRepository();
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    public static ProductRepository getInstance() {
        return INSTANCE;
    }

    {

        productMap.put(1L, new Product(1L, "Product 1", BigDecimal.valueOf(110.05)));
        productMap.put(2L, new Product(2L, "Product 2", BigDecimal.valueOf(20.02)));
        productMap.put(3L, new Product(3L, "Product 3", BigDecimal.valueOf(300.0)));
        productMap.put(4L, new Product(4L, "Product 4", BigDecimal.valueOf(444.44)));
        productMap.put(5L, new Product(5L, "Product 5", BigDecimal.valueOf(55.5)));
    }




    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }


    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            Long id = Collections.max(productMap.keySet()) + 1;
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }


    public Product findById(Long id) { return productMap.get(id); }


    public void deleteById(Long id) {
        productMap.remove(id);
    }
}