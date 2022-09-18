package com.pavikumbhar.jpa.repository;

import com.pavikumbhar.jpa.enums.Status;
import com.pavikumbhar.jpa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {


    List<Product> findByProductCodeAndStatus(String productCode, Status status);

    @Query("SELECT  DISTINCT product FROM  Product product INNER JOIN FETCH  product.productProperties property WHERE" +
            " property.status='Active' AND property.productPropertyCode=?1")
    Product findByProductPropertyCode(String productPropertyCode);


    @Query("SELECT  DISTINCT product FROM  Product product INNER JOIN FETCH  product.productProperties property" +
            " WHERE property.status='Active' AND property.productPropertyCode LIKE %:productPropertyCode%")
    Product findByProductPropertyCodeLike(String productPropertyCode);


    @Query( value = "SELECT  DISTINCT product FROM  Product product INNER JOIN FETCH  product.productProperties property" +
            " WHERE property.status='Active' AND property.productPropertyCode LIKE %:productPropertyCode%"
          ,countQuery = "SELECT  count (DISTINCT product) FROM  Product product INNER JOIN   product.productProperties property" +
            " WHERE property.status='Active' AND property.productPropertyCode LIKE %:productPropertyCode%")
    Page<Product> findByProductPropertyCodeLike(String productPropertyCode, Pageable pageable);

}
