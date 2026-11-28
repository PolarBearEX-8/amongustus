package net.labymod;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
   private static final Gson GSON = new GsonBuilder()
      .registerTypeAdapter(UvUnion.class, new UvUnion.Deserializer())
      .registerTypeAdapter(LocatorValue.class, new LocatorValue.Deserializer())
      .registerTypeAdapter(PolysUnion.class, new PolysUnion.Deserializer())
      .registerTypeAdapter(OffsetDateTime.class, new Converter.TimeDeserializer())
      .create();
   private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
      .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_INSTANT)
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      .toFormatter()
      .withZone(ZoneOffset.UTC);
   private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
      .appendOptional(DateTimeFormatter.ISO_TIME)
      .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
      .parseDefaulting(ChronoField.YEAR, 2020L)
      .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1L)
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 1L)
      .toFormatter()
      .withZone(ZoneOffset.UTC);

   public static RawGeoModel fromJsonString(String str) throws IOException {
      return (RawGeoModel)GSON.fromJson(str, RawGeoModel.class);
   }

   public static OffsetDateTime parseDateTimeString(String str) {
      return ZonedDateTime.from(DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
   }

   public static OffsetTime parseTimeString(String str) {
      return ZonedDateTime.from(TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
   }


   public static class TimeDeserializer implements JsonDeserializer {
      public OffsetDateTime deserialize_m1(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return Converter.parseDateTimeString(elem.toString());
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserialize_m1(elem, type, context);
      }
   }
}
