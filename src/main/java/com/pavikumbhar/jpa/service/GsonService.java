package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.model.OuterClass;

public interface GsonService {
    ProductDto addProduct(ProductDto productDto);

    OuterClass.InnerClass outerClassInnerClass();
}
