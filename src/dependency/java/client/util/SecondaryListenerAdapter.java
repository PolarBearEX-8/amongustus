package client.util;

import client.event.StaticListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class SecondaryListenerAdapter implements StaticListener {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      String text2 = (String)entry;
      this.addProperty(text, text2, jsonObject);
   }

   public String resolveText(JsonPrimitive jsonPrimitive) {
      return jsonPrimitive.getAsString().intern();
   }

   @Override
   public Object resolveTarget(JsonPrimitive jsonPrimitive) {
      return this.resolveText(jsonPrimitive);
   }

   public void addProperty(String text, String text2, JsonObject jsonObject) {
      jsonObject.addProperty(text, text2);
   }
}
