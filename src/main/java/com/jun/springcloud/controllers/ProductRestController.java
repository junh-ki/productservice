package com.jun.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jun.springcloud.dto.CouponDto;
import com.jun.springcloud.model.Product;
import com.jun.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${coupleservice.url}")
	private String couponServiceURL;
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		CouponDto couponDto = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), CouponDto.class);
		product.setPrice(product.getPrice().subtract(couponDto.getDiscount()));
		return repo.save(product);
	}
	
}
