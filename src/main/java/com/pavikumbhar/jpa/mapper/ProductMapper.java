package com.pavikumbhar.jpa.mapper;

import java.util.Collections;
import java.util.List;


import org.springframework.stereotype.Component;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.dto.ProductPropertyDto;
import com.pavikumbhar.jpa.model.Product;
import com.pavikumbhar.jpa.model.ProductProperty;

@Component
public class ProductMapper {
	
	public ProductDto toProductDto(Product product) {
		return ProductDto.builder()
				.productId(product.getProductId())
				.productCode(product.getProductCode())
				.status(product.getStatus())
				.productProperties(toProductPropertyDtoList(product.getProductProperties()))
				.createdBy(product.getCreatedBy())
				.createdOn(product.getCreatedOn())
				.modifiedBy(product.getModifiedBy())
				.modifiedOn(product.getModifiedOn())
				.build();
	}
	
	public Product toProduct(ProductDto productDto) {
		return Product.builder()
				.productId(productDto.getProductId())
				.productCode(productDto.getProductCode())
				.status(productDto.getStatus())
				.productProperties(toProductPropertyList(productDto.getProductProperties()))
				.build();
	}
	
	public ProductPropertyDto toProductPropertyDto(ProductProperty productProperty) {
		return ProductPropertyDto.builder()
				.productPropertyId(productProperty.getProductPropertyId())
				.productPropertyCode(productProperty.getProductPropertyCode())
				.productId(productProperty.getProductId())
				.status(productProperty.getStatus())
				.createdBy(productProperty.getCreatedBy())
				.createdOn(productProperty.getCreatedOn())
				.modifiedBy(productProperty.getModifiedBy())
				.modifiedOn(productProperty.getModifiedOn())
				.build();
	}
	
	public ProductProperty toProductProperty(ProductPropertyDto productPropertyDto) {
		return ProductProperty.builder()
				.productPropertyId(productPropertyDto.getProductPropertyId())
				.productPropertyCode(productPropertyDto.getProductPropertyCode())
				.productId(productPropertyDto.getProductId())
				.status(productPropertyDto.getStatus())
				.build();
	}
	
	public List<ProductProperty> toProductPropertyList(List<ProductPropertyDto> productProperties ){
	return	productProperties!=null?productProperties.stream()
			.map(this::toProductProperty)
			.toList():Collections.emptyList();
	}
	
	public List<ProductPropertyDto> toProductPropertyDtoList(List<ProductProperty> productProperties ){
	return	productProperties!=null?productProperties.stream()
			.map(this::toProductPropertyDto)
			.toList():Collections.emptyList();
	}
	
	
	
	public List<ProductDto> toProductDtoList(List<Product> productList) {
		return productList!=null?productList.stream()
				.map(this::toProductDto)
				.toList():Collections.emptyList();
	}
	
	public List<ProductDto> toProductList(List<Product> productDtoList) {
		return productDtoList!=null?productDtoList.stream()
				.map(this::toProductDto)
				.toList():Collections.emptyList();
	}
}