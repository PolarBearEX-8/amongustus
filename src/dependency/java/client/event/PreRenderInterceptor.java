package client.event;

import com.google.gson.JsonElement;

public interface PreRenderInterceptor {
   void applyJsonElement(JsonElement jsonElement);

   JsonElement getJsonElement();
}
