package client.util;

import client.event.StaticListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class AuxiliaryListenerBridge implements StaticListener {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      Integer count = (Integer)entry;
      this.addProperty(text, count, jsonObject);
   }

   @Override
   public Object resolveTarget(JsonPrimitive jsonPrimitive) {
      return this.resolveInteger(jsonPrimitive);
   }

   public void addProperty(String text, Integer count, JsonObject jsonObject) {
      jsonObject.addProperty(text, count);
   }

   public Integer resolveInteger(JsonPrimitive jsonPrimitive) {
      return jsonPrimitive.getAsInt();
   }
}
