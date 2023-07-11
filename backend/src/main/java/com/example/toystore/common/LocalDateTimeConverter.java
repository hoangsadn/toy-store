package com.example.toystore.common;


import org.sql2o.converters.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    @Override
    public LocalDateTime convert(Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) val).toLocalDateTime();
        } else if (val instanceof String) {
            return LocalDateTime.parse((String) val, FORMATTER);
        } else if (val instanceof LocalDateTime){
            return (LocalDateTime) val;
        } else {
            return null;
        }
    }

    @Override
    public String toDatabaseParam(LocalDateTime val) {
        if (val == null) {
            return null;
        } else {
            return FORMATTER.format(val);
        }
    }
}