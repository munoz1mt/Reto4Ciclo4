package com.usa.ciclo4.reto3ciclo4.repository.crud;

import com.usa.ciclo4.reto3ciclo4.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 *
 * @author Sebastián
 */
public interface ProductCrudRepository extends MongoRepository<Product, String> {
}
