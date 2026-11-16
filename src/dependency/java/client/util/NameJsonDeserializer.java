package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class NameJsonDeserializer implements JsonDeserializer, JsonSerializer {
   public final YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager;

   public JsonElement resolveJsonElement(GameProfileEntry gameProfileEntry, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("name", gameProfileEntry.getGameProfile().getName());
      UUID id = gameProfileEntry.getGameProfile().getId();
      jsonObject.addProperty("uuid", id == null ? "" : id.toString());
      jsonObject.addProperty("expiresOn", YyyyMMDdHHMmSsZManager.simpleDateFormat.format(gameProfileEntry.getDate()));
      return jsonObject;
   }

   public GameProfileEntry resolveGameProfileEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        java.lang.Object entry = null;
      if (jsonElement.isJsonObject()) {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         JsonElement jsonElement2 = asJsonObject.get("name");
         JsonElement jsonElement3 = asJsonObject.get("uuid");
         JsonElement jsonElement4 = asJsonObject.get("expiresOn");
         if (jsonElement2 != null && jsonElement3 != null) {
            String asString = jsonElement3.getAsString();
            String asString2 = jsonElement2.getAsString();
            Date date = null;
            if (jsonElement4 != null) {
               try {
                  date = YyyyMMDdHHMmSsZManager.simpleDateFormat.parse(jsonElement4.getAsString());
               } catch (ParseException parseException) {
                  date = null;
               }
            }

            if (asString2 != null && asString != null) {
               try {
                  entry = UUID.fromString(asString);
               } catch (Throwable throwable) {
                  return null;
               }

               YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager2 = this.yyyyMMDdHHMmSsZManager;
               this.yyyyMMDdHHMmSsZManager.getClass();
               return new GameProfileEntry(yyyyMMDdHHMmSsZManager2, new GameProfile((UUID)(entry), asString2), date, null);
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveGameProfileEntry(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((GameProfileEntry)entry, type, jsonSerializationContext);
   }

   private NameJsonDeserializer(YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager2) {
      this.yyyyMMDdHHMmSsZManager = yyyyMMDdHHMmSsZManager2;
   }

   public NameJsonDeserializer(YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager2, AuxiliaryParameterizedTypeAdapter auxiliaryParameterizedTypeAdapter) {
      this(yyyyMMDdHHMmSsZManager2);
   }
}
