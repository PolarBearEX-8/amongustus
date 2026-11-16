package client.util;

import client.event.StaticListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class TertiaryListenerRegistry implements StaticListener {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      Float factor = (Float)entry;
      this.addProperty(text, factor, jsonObject);
   }

   public void addProperty(String text, Float factor, JsonObject jsonObject) {
      jsonObject.addProperty(text, factor);
   }

   @Override
   public Object resolveTarget(JsonPrimitive jsonPrimitive) {
      return this.resolveFloat(jsonPrimitive);
   }

   public Float resolveFloat(JsonPrimitive jsonPrimitive) {
      return jsonPrimitive.getAsFloat();
   }
}
