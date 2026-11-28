package net.augustus.utils.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.concurrent.CompletableFuture;

public interface IpLookupService {
   Gson GSON = new Gson().newBuilder().create();

   default String getStringOrNull(JsonObject json, String str) {
      return json.has(str) ? json.get(str).getAsString() : null;
   }

   default IpLookupResult lookupUnchecked(String str) {

      try {
         return this.lookup(str);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   IpLookupResult lookup(String str) throws Exception;

   default CompletableFuture lookupAsync(String str) {
      return CompletableFuture.supplyAsync(() -> this.lookupUnchecked(str));
   }

   default Boolean getBooleanOrNull(JsonObject json, String str) {
      return json.has(str) ? json.get(str).getAsBoolean() : null;
   }

   default Double getDoubleOrNull(JsonObject json, String str) {
      return json.has(str) ? json.get(str).getAsDouble() : null;
   }
}
