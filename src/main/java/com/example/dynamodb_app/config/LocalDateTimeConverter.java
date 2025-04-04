package com.example.dynamodb_app.config;


import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public AttributeValue transformFrom(LocalDateTime input) {
        return input != null ? AttributeValue.builder().s(input.format(FORMATTER)).build() : AttributeValue.builder().nul(true).build();
    }

    @Override
    public LocalDateTime transformTo(AttributeValue input) {
        return input.s() != null ? LocalDateTime.parse(input.s(), FORMATTER) : null;
    }

    @Override
    public EnhancedType<LocalDateTime> type() {
        return EnhancedType.of(LocalDateTime.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }
}