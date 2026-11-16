package client.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.concurrent.CompletableFuture;
import net.minecraft.item.IronCrown;

public interface AuxiliaryGsonCallback {
   Gson gson = new Gson().newBuilder().create();

   IronCrown resolveIronCrown(String text) throws Exception;

   default CompletableFuture supplyAsync(String text) {
      return CompletableFuture.supplyAsync(() -> this.resolveIronCrownByText(text));
   }

   default String resolveText(JsonObject jsonObject, String key) {
      return jsonObject.has(key) ? jsonObject.get(key).getAsString() : null;
   }

   default Boolean resolveBoolean(JsonObject jsonObject, String key) {
      return jsonObject.has(key) ? jsonObject.get(key).getAsBoolean() : null;
   }

   default Double resolveDouble(JsonObject jsonObject, String key) {
      return jsonObject.has(key) ? jsonObject.get(key).getAsDouble() : null;
   }

   default IronCrown resolveIronCrownByText(String text) {
      try {
         return this.resolveIronCrown(text);
      } catch (Exception exception) {
         throw new RuntimeException(exception);
      }
   }
}
