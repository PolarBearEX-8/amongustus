package net.minecraft.util;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Locale;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {
   private String lowercase(Object obj) {
      return obj instanceof Enum ? ((Enum)obj).name().toLowerCase(Locale.US) : obj.toString().toLowerCase(Locale.US);
   }

   public TypeAdapter create(Gson gson, TypeToken typeToken) {
      Class rawType = typeToken.getRawType();
      if (!rawType.isEnum()) {
         return null;
      } else {
         HashMap map = Maps.newHashMap();

         for (Object obj : rawType.getEnumConstants()) {
            map.put(this.lowercase(obj), obj);
         }

         return new EnumTypeAdapterFactory.DefaultTypeAdapter(this, map);
      }
   }

   public static String invokeLowercase(EnumTypeAdapterFactory enumTypeAdapterFactory, Object obj) {
      return enumTypeAdapterFactory.lowercase(obj);
   }


   public static class DefaultTypeAdapter extends TypeAdapter {
      public final Map lowercaseToConstant;
      public final EnumTypeAdapterFactory factory;

      public Object read(JsonReader jsonReader) throws IOException {
         if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
         } else {
            return this.lowercaseToConstant.get(jsonReader.nextString());
         }
      }

      public void write(JsonWriter jsonWriter, Object obj) throws IOException {
         if (obj == null) {
            jsonWriter.nullValue();
         } else {
            jsonWriter.value(EnumTypeAdapterFactory.invokeLowercase(this.factory, obj));
         }
      }

      public DefaultTypeAdapter(EnumTypeAdapterFactory enumTypeAdapterFactory, Map map) {
         this.factory = enumTypeAdapterFactory;
         this.lowercaseToConstant = map;
      }
   }
}
