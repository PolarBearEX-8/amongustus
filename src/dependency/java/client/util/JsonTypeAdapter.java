package client.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;

public class JsonTypeAdapter extends TypeAdapter {
   public final Map map;
   public final TypeAdapterFactoryAdapter typeAdapterFactoryAdapter;

   public JsonTypeAdapter(TypeAdapterFactoryAdapter typeAdapterFactoryAdapter2, Map map2) {
      this.typeAdapterFactoryAdapter = typeAdapterFactoryAdapter2;
      this.map = map2;
   }

   public void write(JsonWriter jsonWriter, Object entry) throws IOException {
      if (entry == null) {
         jsonWriter.nullValue();
      } else {
         jsonWriter.value(TypeAdapterFactoryAdapter.resolveText(this.typeAdapterFactoryAdapter, entry));
      }
   }

   public Object read(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() == JsonToken.NULL) {
         jsonReader.nextNull();
         return null;
      } else {
         return this.map.get(jsonReader.nextString());
      }
   }
}
