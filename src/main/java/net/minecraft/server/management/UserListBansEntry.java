package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.UUID;

public class UserListBansEntry extends BanEntry {

   public UserListBansEntry(JsonObject json) {
      super(toGameProfile(json), json);
   }

   private static GameProfile toGameProfile(JsonObject json) {
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

   @Override
   public void onSerialization(JsonObject json) {
      if (this.getValue() != null) {
         json.addProperty("uuid", ((GameProfile)this.getValue()).getId() == null ? "" : ((GameProfile)this.getValue()).getId().toString());
         json.addProperty("name", ((GameProfile)this.getValue()).getName());
         super.onSerialization(json);
      }
   }

   public UserListBansEntry(GameProfile profile) {
      this(profile, (Date)null, (String)null, (Date)null, (String)null);
   }

   public UserListBansEntry(GameProfile profile, Date date, String str, Date date2, String str2) {
      super(profile, date2, str, date2, str2);
   }
}
