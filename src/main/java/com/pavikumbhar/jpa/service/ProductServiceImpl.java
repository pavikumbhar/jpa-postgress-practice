package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.dto.ProductPropertyDto;
import com.pavikumbhar.jpa.enums.Status;
import com.pavikumbhar.jpa.exception.AppException;
import com.pavikumbhar.jpa.mapper.ProductMapper;
import com.pavikumbhar.jpa.model.Product;
import com.pavikumbhar.jpa.model.ProductProperty;
import com.pavikumbhar.jpa.repository.*;
import com.pavikumbhar.jpa.specification.CustomSpecificationBuilder;
import com.pavikumbhar.jpa.specification.Filter;
import com.pavikumbhar.jpa.specification.QueryOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductPropertyRepository productPropertyRepository;
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	private final CustomSpecificationBuilder customSpecificationBuilder;
	
	
	@Transactional
	@Override
	public ProductDto findProductPropertyByProductId(long productId) {
		logger.info("findProductPropertyByProductId  productId: {}",productId);
		Product product = productRepository.findById(productId).orElse(null);
		return productMapper.toProductDto(product);
	}

	@Transactional
	@Override
	public ProductDto findByProductPropertyCode(String productPropertyCode) {
		logger.info("findByProductPropertyCode  productPropertyCode: {}",productPropertyCode);
		Product product=productRepository.findByProductPropertyCodeLike(productPropertyCode);
		if(product==null){
			throw  new AppException("no data found");
		}
		return productMapper.toProductDto(product);
	}

	@Transactional
	@Override
	public ProductDto findByProductPropertyCodeSpec(String productPropertyCode) {
		logger.info("findByProductPropertyCode  productPropertyCode: {}",productPropertyCode);

		Filter productPropertyCodeEqual = Filter.builder()
				.field(Product.Fields.productProperties.name())
				.childField(ProductProperty.Fields.productPropertyCode.name())
				.operator(QueryOperator.EQUALS)
				.value(productPropertyCode)
				.build();

		Specification<Product> spec = customSpecificationBuilder.createChildSpecification(productPropertyCodeEqual,Product.class);
		List<Product>  productList= productRepository.findAll(spec);

		if(productList.isEmpty()){
			throw  new AppException("no data found");
		}
		return productMapper.toProductDto(productList.get(0));
	}



	@Override
	public ProductDto addProduct(ProductDto productDto) {
		logger.info("addProduct  productDto: {}",productDto);
		List< Product> productList=productRepository.findByProductCodeAndStatus(productDto.getProductCode(), Status.Active);
		if(!productList.isEmpty()){
			throw  new AppException("Product already present");
		}
		Product product=productRepository.save(productMapper.toProduct(productDto));
		return productMapper.toProductDto(product);
	}

	@Transactional
	@Override
	public ProductPropertyDto addProductProperty(String productId, ProductPropertyDto productPropertyDto) {
		logger.info("addProductProperty  productPropertyDto: {}",productPropertyDto);
		ProductProperty productProperty = productPropertyRepository.save(productMapper.toProductProperty(productPropertyDto));
		return productMapper.toProductPropertyDto(productProperty);
	}

}
