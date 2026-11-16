package client.util;

import client.event.StaticListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class InternalListenerBridge implements StaticListener {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      Boolean flag = (Boolean)entry;
      this.addProperty(text, flag, jsonObject);
   }

   public void addProperty(String text, Boolean flag, JsonObject jsonObject) {
      jsonObject.addProperty(text, flag);
   }

   @Override
   public Object resolveTarget(JsonPrimitive jsonPrimitive) {
      return this.resolveBoolean(jsonPrimitive);
   }

   public Boolean resolveBoolean(JsonPrimitive jsonPrimitive) {
      return jsonPrimitive.getAsBoolean();
   }
}
