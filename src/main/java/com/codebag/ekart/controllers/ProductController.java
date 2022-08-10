package com.codebag.ekart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebag.ekart.entity.Product;
import com.codebag.ekart.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	@GetMapping("/product/{id}")
	public Optional<Product> findProductById(@PathVariable Integer id) {
		return productRepository.findById(id);
	}

	@PostMapping("/product")
	public Product saveProduct(@RequestBody Product product) {
		product = productRepository.save(product);
		return product;
	}

}
