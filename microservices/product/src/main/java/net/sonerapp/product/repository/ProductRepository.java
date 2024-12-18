package net.sonerapp.product.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.sonerapp.product.entity.Product;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    boolean existsByProductId(int productId);

    Optional<Product> findByProductId(int productId);
}
