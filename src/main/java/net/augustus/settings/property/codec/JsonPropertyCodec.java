package net.augustus.settings.property.codec;

import com.google.gson.JsonObject;

public interface JsonPropertyCodec {
   void write(String str, Object obj, JsonObject json);

   Object read(String str, Object obj, JsonObject json);
}
