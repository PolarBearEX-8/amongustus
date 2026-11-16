package client.util;

import com.google.gson.JsonObject;

public interface JsonPropertyGetter {
   void applyText(String text, Object entry, JsonObject jsonObject);

   Object getEntry(String text, Object entry, JsonObject jsonObject);
}
