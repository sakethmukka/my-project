package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // You can define custom query methods here if needed
}
