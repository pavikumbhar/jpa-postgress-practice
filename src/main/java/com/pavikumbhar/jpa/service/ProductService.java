package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.dto.ProductPropertyDto;



public interface ProductService {

	ProductDto findProductPropertyByProductId(long productId);

	ProductDto findByProductPropertyCode(String productPropertyCode);

    ProductDto findByProductPropertyCodeSpec(String productPropertyCode);

    ProductDto addProduct(ProductDto productDto);


    ProductPropertyDto addProductProperty(String productId, ProductPropertyDto productPropertyDto);
}