package com.pavikumbhar.jpa.repository;

import com.pavikumbhar.jpa.enums.Status;
import com.pavikumbhar.jpa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {


        List<Product> findByProductCodeAndStatus(String productCode, Status status);
        @Query("SELECT  DISTINCT product FROM  Product product INNER JOIN FETCH  product.productProperties property WHERE" +
        " LOWER(property.status)=LOWER('Active') AND property.productPropertyCode=?1")
        Product findByProductPropertyCode(@Param("productPropertyCode")  String productPropertyCode);


        @Query("SELECT  DISTINCT product FROM  Product product INNER JOIN FETCH  product.productProperties property" +
        " WHERE LOWER(property.status)=LOWER('Active') AND property.productPropertyCode LIKE %:productPropertyCode%")
        Product findByProductPropertyCodeLike(@Param("productPropertyCode") String productPropertyCode);


        /**
         *  Hibernate HHH000104 firstResult maxResults warning using Spring Data JPA
         * @param productPropertyCode
         * @param pageable
         * @return
         */
        @Query( value = "SELECT  DISTINCT product FROM  Product product INNER JOIN FETCH  product.productProperties property" +
        " WHERE LOWER(property.status)=LOWER('Active') AND property.productPropertyCode LIKE %:productPropertyCode%"
        ,countQuery = "SELECT  count (DISTINCT product) FROM  Product product INNER JOIN   product.productProperties property" +
        " WHERE LOWER(property.status)=LOWER('Active') AND property.productPropertyCode LIKE %:productPropertyCode%")
        Page<Product> findByProductPropertyCodeLike(String productPropertyCode, Pageable pageable);
        @Query( value = "SELECT  DISTINCT product.productId FROM  Product product INNER JOIN product.productProperties property" +
                " WHERE LOWER(property.status)=LOWER('Active') AND property.productPropertyCode LIKE %:productPropertyCode%")
        Page<Long> findProductIdsByProductPropertyCodeLike(@Param("productPropertyCode") String productPropertyCode, Pageable pageable);

        @Query( value = "SELECT  DISTINCT product FROM  Product product INNER JOIN product.productProperties property" +
                " WHERE LOWER(property.status)=LOWER('Active') " +
                " AND property.productPropertyCode LIKE %:productPropertyCode%" +
                " AND product.productId IN (:ids)")
        List<Product> findByProductPropertyCodeLikeInProductId(@Param("productPropertyCode") String productPropertyCode, @Param("ids") List<Long> ids);

}
