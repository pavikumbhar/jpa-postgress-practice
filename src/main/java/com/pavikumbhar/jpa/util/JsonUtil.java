package com.pavikumbhar.jpa.util;


import java.io.IOException;
import java.text.DateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


import com.pavikumbhar.jpa.exception.AppException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author pavikumbhar
 *
 */
@Slf4j
@UtilityClass
public class JsonUtil {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
                                      .registerModule(new ParameterNamesModule())
                                      .registerModule(new Jdk8Module())
                                      .registerModule(new JavaTimeModule());


    private static final Gson GSON =new Gson();


    /**
     * This method serializes the specified object into its equivalent Json representation.
     *
     * @param t
     * @return
     */
    public static <T> String toJson(T t) {

        try {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
                    .serializeNulls().setDateFormat(DateFormat.LONG)
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .setPrettyPrinting()
                    .setVersion(1.0)
                    .create();
            return gson.toJson(t);
        } catch (Exception e) {
            logger.error("Exception while Json serialization");
            throw new AppException(e.getMessage());
        }

    }

    /**
     * This method deserializes the specified Json into an object of the specified class.
     *
     * @param json
     * @param classType
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classType) {
        try {
            return GSON.fromJson(json, classType);
        } catch (JsonSyntaxException e) {
            logger.error("Exception while Json deserialization");
            throw new AppException(e.getMessage());
        }
    }

    /**
     *
     * @param t
     * @return
     * @param <T>
     */
    public static <T> String fromEntityToJson(T t) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            logger.error("Exception fromEntityToJson {}",e);
            throw new AppException(e.getMessage());
        }
    }

    /**
     *
     * @param json
     * @param classType
     * @return
     * @param <T>
     */
    public static <T> T fromJsonToEntity(String json, Class<T> classType) {
        try {
            return OBJECT_MAPPER.readValue(json, classType);
        } catch (IOException e) {
            logger.error("Exception fromJsonToEntity {}",e);
            throw new AppException(e.getMessage());
        }


    }

}