package client.event;

import com.google.gson.JsonObject;

public interface PostRenderDispatcher {
   int getCount();

   void applyJsonObject(JsonObject jsonObject);

   JsonObject resolveJsonObject(String text);

   String[] resolveTextArray(JsonObject jsonObject);
}
