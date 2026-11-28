package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class UserListWhitelistEntry extends UserListEntry {

   @Override
   public void onSerialization(JsonObject json) {
      if (this.getValue() != null) {
         json.addProperty("uuid", ((GameProfile)this.getValue()).getId() == null ? "" : ((GameProfile)this.getValue()).getId().toString());
         json.addProperty("name", ((GameProfile)this.getValue()).getName());
         super.onSerialization(json);
      }
   }

   public UserListWhitelistEntry(GameProfile profile) {
      super(profile);
   }

   public UserListWhitelistEntry(JsonObject json) {
      super(gameProfileFromJsonObject(json), json);
   }

   private static GameProfile gameProfileFromJsonObject(JsonObject json) {
      if (json.has("uuid") && json.has("name")) {
         String asString = json.get("uuid").getAsString();

         UUID uuid;
         try {
            uuid = UUID.fromString(asString);
         } catch (Throwable e) {
            return null;
         }

         return new GameProfile(uuid, json.get("name").getAsString());
      } else {
         return null;
      }
   }
}
