//package com.example.springboot.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.text.SimpleDateFormat;
//import java.util.TimeZone;
//
//@Configuration
//public class JacksonConfig {
//
//    /**
//     * 解决主键Long类型返回给页面时，页面精度丢失的问题,时间格式化返回
//     */
//    @Bean
//    public ObjectMapper objectMapper() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.failOnEmptyBeans(false).failOnUnknownProperties(false);
//        ObjectMapper objectMapper = builder.build();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance)
//                .addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        return objectMapper;
//    }
//}