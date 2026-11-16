package client.util;

import client.event.StaticListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class ListenerBridge implements StaticListener {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      Double offset = (Double)entry;
      this.addProperty(text, offset, jsonObject);
   }

   public void addProperty(String text, Double offset, JsonObject jsonObject) {
      jsonObject.addProperty(text, offset);
   }

   @Override
   public Object resolveTarget(JsonPrimitive jsonPrimitive) {
      return this.resolveDouble(jsonPrimitive);
   }

   public Double resolveDouble(JsonPrimitive jsonPrimitive) {
      return jsonPrimitive.getAsDouble();
   }
}
