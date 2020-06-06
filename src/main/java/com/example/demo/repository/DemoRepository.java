package com.example.demo.repository;

import com.example.demo.repository.dao.Checkout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends MongoRepository<Checkout, String> {

    Checkout findByUpc(String upc);
}
