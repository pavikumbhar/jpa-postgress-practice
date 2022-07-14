package com.pavikumbhar.jpa.repository;

import com.pavikumbhar.jpa.enums.Status;
import com.pavikumbhar.jpa.model.Product;
import com.pavikumbhar.jpa.model.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPropertyRepository  extends JpaRepository<ProductProperty, Long>{
	
	List<ProductProperty> findByProductIdAndStatus(long productId, Status status);
	
	@Query("SELECT  prop.product FROM  ProductProperty prop WHERE prop.productPropertyCode=?1")
	Product findByProductPropertyCode(String productPropertyCode);


}
