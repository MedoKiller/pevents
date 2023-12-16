package com.pardus.pevents.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

  @Value("${am.api.json.datetime_format:yyyy-MM-dd'T'HH:mm:ss.SSS}")
  private String dateTimeFormat;

  @Value("${am.api.json.timezone:Europe/London}")
  private TimeZone timeZone;

  @Value("${am.api.json.timezone:Europe/London}")
  private String timeZoneString;

  private static final String RANGE_PARSE_EXCEPTION_MESSAGE = "Range could not be parsed";

  @Autowired
  public JacksonConfig() {
    // default constructor
  }

  @Bean
  public Module jacksonDateModule() {
    SimpleModule module = new SimpleModule("CustomRangeModule", Version.unknownVersion());

    addModuleForSerializeTimestamp(module);

    addModuleForDeserializeTimestamp(module);

    return module;
  }

  private void addModuleForDeserializeTimestamp(SimpleModule module) {
    module.addDeserializer(Timestamp.class, new JsonDeserializer<Timestamp>() {
      @Override
      public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        if (date != null && !date.isEmpty()) {
          try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
            sdf.setTimeZone(timeZone);
            return new Timestamp(sdf.parse(date).getTime());
          } catch (ParseException e) {
            throw new DateDeserializationException("Date could not be parsed", e);
          }
        }
        return null;
      }

      @Override
      public Timestamp deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
              throws IOException {
        return (Timestamp) typeDeserializer.deserializeTypedFromAny(p, ctxt);
      }
    });
  }

  private void addModuleForSerializeTimestamp(SimpleModule module) {
    module.addSerializer(Timestamp.class, new JsonSerializer<Timestamp>() {
      @Override
      public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
          SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
          sdf.setTimeZone(timeZone);
          gen.writeString(sdf.format(new Date(value.getTime())));
        } else {
          gen.writeNull();
        }
      }

      @Override
      public void serializeWithType(Timestamp value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer)
              throws IOException {
        if (value != null) {
          typeSer.writeTypePrefix(gen, typeSer.typeId(value, JsonToken.START_OBJECT));
          serialize(value, gen, serializers);
          typeSer.writeTypeSuffix(gen, typeSer.typeId(value, JsonToken.START_OBJECT));
        } else {
          gen.writeNull();
        }
      }
    });
  }


  @Bean
  @Primary
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
    b.modulesToInstall(jacksonDateModule());
    return b;
  }

}
