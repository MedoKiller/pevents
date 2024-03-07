package com.pardus.pevents.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/*
klasa koja na sve instance OffsetDateTime-a stavlja JVM systemDefault() offset
napravljena zbog toga što retrieval iz postgrea kolone timestamptz nije ispravno mapirao offset u OffsetDatetime nego se info o offsetu gubila i retriveal je uvijek bio UTC
možda ima bolji i elegantniji način ...
 */

@Converter(autoApply = true)
public class OffsetDateTimeConverter implements AttributeConverter<OffsetDateTime, OffsetDateTime> {

    @Override
    public OffsetDateTime convertToDatabaseColumn(OffsetDateTime attribute) {
        // Assuming you want to store the times in the database in UTC
        //return attribute != null ? attribute.withOffsetSameInstant(ZoneOffset.UTC) : null;
        return attribute != null ? attribute.withOffsetSameInstant(ZonedDateTime.now(ZoneId.systemDefault()).getOffset()) : null;
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(OffsetDateTime dbData) {
        // Convert from UTC to system's default time zone offset
        return dbData != null ? dbData.withOffsetSameInstant(ZonedDateTime.now(ZoneId.systemDefault()).getOffset()) : null;
    }
}