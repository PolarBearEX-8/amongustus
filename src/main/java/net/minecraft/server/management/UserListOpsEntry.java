package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class UserListOpsEntry extends UserListEntry {
   private final int permissionLevel;
   private final boolean bypassesPlayerLimit;

   public UserListOpsEntry(GameProfile profile, int n, boolean flag) {
      super(profile);
      this.permissionLevel = n;
      this.bypassesPlayerLimit = flag;
   }

   @Override
   public void onSerialization(JsonObject json) {
      if (this.getValue() != null) {
         json.addProperty("uuid", ((GameProfile)this.getValue()).getId() == null ? "" : ((GameProfile)this.getValue()).getId().toString());
         json.addProperty("name", ((GameProfile)this.getValue()).getName());
         super.onSerialization(json);
         json.addProperty("level", this.permissionLevel);
         json.addProperty("bypassesPlayerLimit", this.bypassesPlayerLimit);
      }
   }

   public UserListOpsEntry(JsonObject json) {
      super(constructProfile(json), json);
      this.permissionLevel = json.has("level") ? json.get("level").getAsInt() : 0;
      this.bypassesPlayerLimit = json.has("bypassesPlayerLimit") && json.get("bypassesPlayerLimit").getAsBoolean();
   }

   public int getPermissionLevel() {
      return this.permissionLevel;
   }

   private static GameProfile constructProfile(JsonObject json) {
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

   public boolean isBypassesPlayerLimit() {
      return this.bypassesPlayerLimit;
   }
}
