package com.pavikumbhar.jpa.controller;

import com.pavikumbhar.jpa.dto.ProductPropertyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product/")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/")
	public String string( ) {
		return "index";
		
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> findProductPropertyByProductId(@PathVariable("productId") long productId ) {
		logger.info("findProductPropertyByProductId  productId: {}",productId);
		ProductDto productDto = productService.findProductPropertyByProductId(productId);
		return ResponseEntity.ok(productDto);
	}
	
	
	@PostMapping("/")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto ) {
		logger.info("addProduct  ");
		ProductDto productResponse = productService.addProduct(productDto);
		return ResponseEntity.ok(productResponse);
	}
	
	
	@GetMapping("/property")
	public ResponseEntity<ProductDto> findByProductPropertyCode(@RequestParam("productPropertyCode") String productPropertyCode ) {
		logger.info("findByProductPropertyCode  productPropertyCode: {}",productPropertyCode);
		ProductDto productDto = productService.findByProductPropertyCode(productPropertyCode);
		return ResponseEntity.ok(productDto);
	}

	@PostMapping("/{productId}/productProperty")
	public ResponseEntity<ProductPropertyDto> addProductProperty(@PathVariable String productId, @RequestBody ProductPropertyDto productPropertyDto){
		logger.info("addProductProperty  productId: {},productPropertyDto:{}",productId,productPropertyDto);
		ProductPropertyDto productProperty	=productService.addProductProperty(productId,productPropertyDto);

		return  ResponseEntity.ok(productProperty);
	}

}
