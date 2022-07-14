package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.dto.ProductPropertyDto;
import com.pavikumbhar.jpa.enums.Status;
import com.pavikumbhar.jpa.mapper.ProductMapper;
import com.pavikumbhar.jpa.model.Product;
import com.pavikumbhar.jpa.model.ProductProperty;
import com.pavikumbhar.jpa.repository.ProductPropertyRepository;
import com.pavikumbhar.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	final ProductPropertyRepository productPropertyRepository;
	final ProductRepository productRepository;
	final ProductMapper productMapper;
	
	
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
			throw  new RuntimeException("no data found");
		}
		return productMapper.toProductDto(product);
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		logger.info("addProduct  productDto: {}",productDto);
		List< Product> productList=productRepository.findByProductCodeAndStatus(productDto.getProductCode(), Status.Active);
		if(!productList.isEmpty()){
			throw  new RuntimeException("Product already present");
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
