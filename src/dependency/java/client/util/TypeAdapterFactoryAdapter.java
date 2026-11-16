package client.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Locale;

public class TypeAdapterFactoryAdapter implements TypeAdapterFactory {
   public static String resolveText(TypeAdapterFactoryAdapter typeAdapterFactoryAdapter, Object entry) {
      return typeAdapterFactoryAdapter.resolveText(entry);
   }

   private String resolveText(Object entry) {
      return entry instanceof Enum ? ((Enum)entry).name().toLowerCase(Locale.US) : entry.toString().toLowerCase(Locale.US);
   }

   public TypeAdapter create(Gson gson, TypeToken typeToken) {
      Class rawType = typeToken.getRawType();
      if (!rawType.isEnum()) {
         return null;
      } else {
         HashMap map = Maps.newHashMap();

         for (Object element : rawType.getEnumConstants()) {
            map.put(this.resolveText(element), element);
         }

         return new JsonTypeAdapter(this, map);
      }
   }
}
