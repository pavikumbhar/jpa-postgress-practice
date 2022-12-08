package com.pavikumbhar.jpa.controller;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.model.OuterClass;
import com.pavikumbhar.jpa.service.GsonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("gson")
public class GsonController {

    private final GsonService gsonService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto ) {
        logger.info("addProduct  ");
         ProductDto products = gsonService.addProduct(productDto);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity< OuterClass.InnerClass> outerClassInnerClass( ) {
        logger.info("addProduct  ");
        OuterClass.InnerClass outerClass= gsonService.outerClassInnerClass();
        return ResponseEntity.ok(outerClass);
    }

}
