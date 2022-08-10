package com.codebag.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebag.ekart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
