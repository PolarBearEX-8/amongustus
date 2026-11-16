package client.util;

import client.event.StaticListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class ExtendedListenerBridge implements StaticListener {
   public void addProperty(String text, Long value, JsonObject jsonObject) {
      jsonObject.addProperty(text, value);
   }

   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      Long value = (Long)entry;
      this.addProperty(text, value, jsonObject);
   }

   public Long resolveLong(JsonPrimitive jsonPrimitive) {
      return jsonPrimitive.getAsLong();
   }

   @Override
   public Object resolveTarget(JsonPrimitive jsonPrimitive) {
      return this.resolveLong(jsonPrimitive);
   }
}
