package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;

public class MaxJsonDeserializer implements JsonDeserializer, JsonSerializer {
   public JsonElement resolveJsonElement(GameProfileArrayHolder gameProfileArrayHolder, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("max", gameProfileArrayHolder.getCount());
      jsonObject.addProperty("online", gameProfileArrayHolder.getCount2());
      if (gameProfileArrayHolder.getGameProfileArray() != null && gameProfileArrayHolder.getGameProfileArray().length > 0) {
         JsonArray jsonArray = new JsonArray();

         for (int index = 0; index < gameProfileArrayHolder.getGameProfileArray().length; index++) {
            JsonObject jsonObject2 = new JsonObject();
            UUID id = gameProfileArrayHolder.getGameProfileArray()[index].getId();
            jsonObject2.addProperty("id", id == null ? "" : id.toString());
            jsonObject2.addProperty("name", gameProfileArrayHolder.getGameProfileArray()[index].getName());
            jsonArray.add(jsonObject2);
         }

         jsonObject.add("sample", jsonArray);
      }

      return jsonObject;
   }

   public GameProfileArrayHolder resolveGameProfileArrayHolder(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "players");
      GameProfileArrayHolder gameProfileArrayHolder = new GameProfileArrayHolder(MissingManager.computeCount(jsonObject, "max"), MissingManager.computeCount(jsonObject, "online"));
      if (MissingManager.matchesJsonObject2(jsonObject, "sample")) {
         JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, "sample");
         if (jsonArray.size() > 0) {
            GameProfile[] gameProfileArray = new GameProfile[jsonArray.size()];

            for (int index = 0; index < gameProfileArray.length; index++) {
               JsonObject jsonObject2 = MissingManager.resolveJsonObject(jsonArray.get(index), "player[" + index + "]");
               String text = MissingManager.resolveText(jsonObject2, "id");
               gameProfileArray[index] = new GameProfile(UUID.fromString(text), MissingManager.resolveText(jsonObject2, "name"));
            }

            gameProfileArrayHolder.setGameProfileArray(gameProfileArray);
         }
      }

      return gameProfileArrayHolder;
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveGameProfileArrayHolder(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((GameProfileArrayHolder)entry, type, jsonSerializationContext);
   }
}
