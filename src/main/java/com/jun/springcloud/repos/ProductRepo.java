package com.jun.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jun.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
