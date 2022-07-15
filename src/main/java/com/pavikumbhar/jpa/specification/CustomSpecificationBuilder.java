package com.pavikumbhar.jpa.specification;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavikumbhar.jpa.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
@Slf4j
@Component
@RequiredArgsConstructor
public  class CustomSpecificationBuilder {
    
    private final ObjectMapper objectMapper;

    public <T> Specification<T> getAndSpecificationFromFilters(List<Filter> filter, Class<T> clazz) {
        Specification<T> specification = where(createSpecification(filter.remove(0),clazz));
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input,clazz));
        }
        return specification;
    }

    public <T>  Specification<T> getOrSpecificationFromFilters(List<Filter> filter, Class<T> clazz) {
        Specification<T> specification = where(createSpecification(filter.remove(0),clazz));
        for (Filter input : filter) {
            specification = specification.or(createSpecification(input,clazz));
        }
        return specification;
    }

    private <T> Specification<T> createSpecification(Filter input, Class<T> clazz) {
        logger.debug("start of createSpecification creating specification for class {} ",clazz.getName());
        switch (input.getOperator()){
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case NOT_EQ:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), "%"+input.getValue()+"%");
            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
            default:
                throw new AppException("Operation not supported yet");
        }
    }

    public static Specification<Object> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }
    @SuppressWarnings("unchecked")
    public  <T> Specification<T> createChildSpecification(Filter input, Class<T> clazz) {
        logger.debug("start of createChildSpecification creating specification for class {} ",clazz.getName());
        switch (input.getOperator()){
            case EQUALS:
                return (root, query, criteriaBuilder) ->{
                    Join<Object, Object> fetchObj = (Join<Object, Object>) root.fetch(input.getField());
                    Path<Object> childField = fetchObj.get(input.getChildField());
                    return criteriaBuilder.equal(childField, castToRequiredType(childField.getJavaType(), input.getValue()));
                };
            case NOT_EQ:
                return (root, query, criteriaBuilder) ->{
                    Join<Object, Object> fetchObj = (Join<Object, Object>) root.fetch(input.getField());
                    Path<Object> childField = fetchObj.get(input.getChildField());
                    return criteriaBuilder.notEqual(childField, castToRequiredType(childField.getJavaType(), input.getValue()));
                };

            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->{
                    Join<Object, Object> fetchObj = (Join<Object, Object>) root.fetch(input.getField());
                    return criteriaBuilder.gt(fetchObj.get(input.getChildField()),
                            (Number) castToRequiredType(fetchObj.get(input.getChildField()).getJavaType(), input.getValue()));
                };

            case LESS_THAN:
                return (root, query, criteriaBuilder) ->{
                    Join<Object, Object> fetchObj = (Join<Object, Object>) root.fetch(input.getField());
                    return criteriaBuilder.lt(fetchObj.get(input.getChildField()),
                            (Number) castToRequiredType(fetchObj.get(input.getChildField()).getJavaType(), input.getValue()));
                };

            case LIKE:
                return (root, query, criteriaBuilder) ->{
                    Join<Object, Object> fetchObj = (Join<Object, Object>) root.fetch(input.getField());
                    return criteriaBuilder.like(fetchObj.get(input.getChildField()), "%"+input.getValue()+"%");
                };

            case IN:
                return (root, query, criteriaBuilder) ->{
                    Join<Object, Object> fetchObj = (Join<Object, Object>) root.fetch(input.getField());
                    Path<Object> childField = fetchObj.get(input.getChildField());
                    return criteriaBuilder.in(childField)
                            .value(castToRequiredType(childField.getJavaType(), input.getValues()));
                };

            default:
                throw new AppException("Operation not supported yet");
        }
    }


    private Object castToRequiredType(Class<?> fieldType, String value) {
        /***
        if(fieldType.isAssignableFrom(Double.class)|| fieldType.isAssignableFrom(double.class)){
            return Double.valueOf(value);
        }else if(fieldType.isAssignableFrom(Integer.class)|| fieldType.isAssignableFrom(int.class)){
            return Integer.valueOf(value);
        }else if(fieldType.isAssignableFrom(Long.class) || fieldType.isAssignableFrom(long.class) ){
            return Long.valueOf(value);
        }else if(Enum.class.isAssignableFrom(fieldType)){
           return Enum.valueOf(fieldType,value);
        }
        return value;
         **/
        logger.debug("value {} , fieldType {}",value, fieldType);
        return objectMapper.convertValue(value, fieldType);

    }

    private Object castToRequiredType(Class<?> fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

}
