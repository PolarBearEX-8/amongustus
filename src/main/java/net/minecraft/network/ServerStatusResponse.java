package net.minecraft.network;

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
import net.minecraft.util.JsonUtils;
import net.minecraft.util.IChatComponent;

public class ServerStatusResponse {
   private IChatComponent serverMotd;
   private ServerStatusResponse.PlayerCountData playerCount;
   private ServerStatusResponse.MinecraftProtocolVersionIdentifier protocolVersion;
   private String favicon;

   public void setProtocolVersionInfo(ServerStatusResponse.MinecraftProtocolVersionIdentifier identifier) {
      this.protocolVersion = identifier;
   }

   public void setFavicon(String str) {
      this.favicon = str;
   }

   public String getFavicon() {
      return this.favicon;
   }

   public ServerStatusResponse.PlayerCountData getPlayerCountData() {
      return this.playerCount;
   }

   public ServerStatusResponse.MinecraftProtocolVersionIdentifier getProtocolVersionInfo() {
      return this.protocolVersion;
   }

   public void setPlayerCountData(ServerStatusResponse.PlayerCountData playerCountData) {
      this.playerCount = playerCountData;
   }

   public IChatComponent getServerDescription() {
      return this.serverMotd;
   }

   public void setServerDescription(IChatComponent component) {
      this.serverMotd = component;
   }


   public static class Serializer implements JsonDeserializer, JsonSerializer {

      public JsonElement serialize(ServerStatusResponse serverStatusResponse, Type type, JsonSerializationContext jsonSerializationContext) {
         JsonObject json = new JsonObject();
         if (serverStatusResponse.getServerDescription() != null) {
            json.add("description", jsonSerializationContext.serialize(serverStatusResponse.getServerDescription()));
         }

         if (serverStatusResponse.getPlayerCountData() != null) {
            json.add("players", jsonSerializationContext.serialize(serverStatusResponse.getPlayerCountData()));
         }

         if (serverStatusResponse.getProtocolVersionInfo() != null) {
            json.add("version", jsonSerializationContext.serialize(serverStatusResponse.getProtocolVersionInfo()));
         }

         if (serverStatusResponse.getFavicon() != null) {
            json.addProperty("favicon", serverStatusResponse.getFavicon());
         }

         return json;
      }

      public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
         return this.serialize((ServerStatusResponse)obj, type, jsonSerializationContext);
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeStatusResponse(elem, type, context);
      }

      public ServerStatusResponse deserializeStatusResponse(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject jsonObject = JsonUtils.getJsonObject(elem, "status");
         ServerStatusResponse serverStatusResponse = new ServerStatusResponse();
         if (jsonObject.has("description")) {
            serverStatusResponse.setServerDescription((IChatComponent)context.deserialize(jsonObject.get("description"), IChatComponent.class));
         }

         if (jsonObject.has("players")) {
            serverStatusResponse.setPlayerCountData((ServerStatusResponse.PlayerCountData)context.deserialize(jsonObject.get("players"), ServerStatusResponse.PlayerCountData.class));
         }

         if (jsonObject.has("version")) {
            serverStatusResponse.setProtocolVersionInfo(
               (ServerStatusResponse.MinecraftProtocolVersionIdentifier)context.deserialize(
                  jsonObject.get("version"), ServerStatusResponse.MinecraftProtocolVersionIdentifier.class
               )
            );
         }

         if (jsonObject.has("favicon")) {
            serverStatusResponse.setFavicon(JsonUtils.getString(jsonObject, "favicon"));
         }

         return serverStatusResponse;
      }
   }

   public static class PlayerCountData {
      private final int maxPlayers;
      private final int onlinePlayerCount;
      private GameProfile[] players;

      public GameProfile[] getPlayers() {
         return this.players;
      }

      public void setPlayers(GameProfile[] profiles) {
         this.players = profiles;
      }

      public int getOnlinePlayerCount() {
         return this.onlinePlayerCount;
      }

      public int getMaxPlayers() {
         return this.maxPlayers;
      }

      public PlayerCountData(int n, int n2) {
         this.maxPlayers = n;
         this.onlinePlayerCount = n2;
      }


      public static class Serializer implements JsonDeserializer, JsonSerializer {

         public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.serialize((ServerStatusResponse.PlayerCountData)obj, type, jsonSerializationContext);
         }

         public JsonElement serialize(ServerStatusResponse.PlayerCountData playerCountData, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject json = new JsonObject();
            json.addProperty("max", playerCountData.getMaxPlayers());
            json.addProperty("online", playerCountData.getOnlinePlayerCount());
            if (playerCountData.getPlayers() != null && playerCountData.getPlayers().length > 0) {
               JsonArray array = new JsonArray();

               for (int i = 0; i < playerCountData.getPlayers().length; i++) {
                  JsonObject json2 = new JsonObject();
                  UUID id = playerCountData.getPlayers()[i].getId();
                  json2.addProperty("id", id == null ? "" : id.toString());
                  json2.addProperty("name", playerCountData.getPlayers()[i].getName());
                  array.add(json2);
               }

               json.add("sample", array);
            }

            return json;
         }

         public ServerStatusResponse.PlayerCountData deserializePlayerCountData(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = JsonUtils.getJsonObject(elem, "players");
            ServerStatusResponse.PlayerCountData playerCountData = new ServerStatusResponse.PlayerCountData(JsonUtils.getInt(jsonObject, "max"), JsonUtils.getInt(jsonObject, "online"));
            if (JsonUtils.isJsonArray(jsonObject, "sample")) {
               JsonArray jsonArray = JsonUtils.getJsonArray(jsonObject, "sample");
               if (jsonArray.size() > 0) {
                  GameProfile[] profiles = new GameProfile[jsonArray.size()];

                  for (int i = 0; i < profiles.length; i++) {
                     JsonObject jsonObject2 = JsonUtils.getJsonObject(jsonArray.get(i), "player[" + i + "]");
                     String string = JsonUtils.getString(jsonObject2, "id");
                     profiles[i] = new GameProfile(UUID.fromString(string), JsonUtils.getString(jsonObject2, "name"));
                  }

                  playerCountData.setPlayers(profiles);
               }
            }

            return playerCountData;
         }

         public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializePlayerCountData(elem, type, context);
         }
      }
   }

   public static class MinecraftProtocolVersionIdentifier {
      private final String name;
      private final int protocol;

      public int getProtocol() {
         return this.protocol;
      }

      public String getName() {
         return this.name;
      }

      public MinecraftProtocolVersionIdentifier(String str, int n) {
         this.name = str;
         this.protocol = n;
      }


      public static class Serializer implements JsonDeserializer, JsonSerializer {

         public JsonElement serialize(ServerStatusResponse.MinecraftProtocolVersionIdentifier identifier, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject json = new JsonObject();
            json.addProperty("name", identifier.getName());
            json.addProperty("protocol", identifier.getProtocol());
            return json;
         }

         public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeProtocolVersion(elem, type, context);
         }

         public ServerStatusResponse.MinecraftProtocolVersionIdentifier deserializeProtocolVersion(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = JsonUtils.getJsonObject(elem, "version");
            return new ServerStatusResponse.MinecraftProtocolVersionIdentifier(JsonUtils.getString(jsonObject, "name"), JsonUtils.getInt(jsonObject, "protocol"));
         }

         public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.serialize((ServerStatusResponse.MinecraftProtocolVersionIdentifier)obj, type, jsonSerializationContext);
         }
      }
   }
}
