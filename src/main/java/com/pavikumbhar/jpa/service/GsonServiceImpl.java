package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.ProductDto;
import com.pavikumbhar.jpa.model.JsonStore;
import com.pavikumbhar.jpa.model.OuterClass;
import com.pavikumbhar.jpa.model.OuterClass.InnerClass;
import com.pavikumbhar.jpa.repository.JsonStoreRepository;
import com.pavikumbhar.jpa.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GsonServiceImpl implements  GsonService{

    private final JsonStoreRepository jsonStoreRepository;
    @Override
    public ProductDto addProduct(ProductDto productDto) {
        String gsonJsonStoreJson =JsonUtil.fromEntityToJson(productDto);
        JsonStore gsonJsonStore = JsonStore.builder()
                                        .jsonString(gsonJsonStoreJson)
                                        .build();
        JsonStore gsonJsonStoreEntity = jsonStoreRepository.save(gsonJsonStore);
        String jsonProductString = gsonJsonStoreEntity.getJsonString();
        ProductDto jsonProductObject = JsonUtil.fromJsonToEntity(jsonProductString, ProductDto.class);
        return jsonProductObject;
    }

    @Override
    public OuterClass.InnerClass outerClassInnerClass() {

        OuterClass outerClass = new OuterClass();
       /*
        outerClass.setValue1("value1");
        outerClass.setValue2("value2");
       */
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.value3 = "value3";
        innerClass.value4 = "value4";
      //  outerClass.setInnerClass(innerClass);

        String sonString =JsonUtil.fromEntityToJson(innerClass);
        JsonStore gsonJsonStore = JsonStore.builder()
                .jsonString(sonString)
                .build();
        JsonStore gsonJsonStoreEntity = jsonStoreRepository.save(gsonJsonStore);
        String jsonProductString = gsonJsonStoreEntity.getJsonString();
      //  OuterClass.InnerClass jsonToObject = JsonUtil.fromJsonToEntity(jsonProductString, OuterClass.InnerClass.class);
        OuterClass.InnerClass jsonToObject = JsonUtil.fromJson(jsonProductString, OuterClass.InnerClass.class);
        return jsonToObject;
    }
}
