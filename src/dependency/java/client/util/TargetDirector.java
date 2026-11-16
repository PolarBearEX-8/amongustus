package client.util;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class TargetDirector extends TargetStack {
   public final int count;
   public final boolean enabled;

   private static GameProfile resolveGameProfile(JsonObject jsonObject) {
        java.lang.Object entry = null;
      if (jsonObject.has("uuid") && jsonObject.has("name")) {
         String asString = jsonObject.get("uuid").getAsString();

         try {
            entry = UUID.fromString(asString);
         } catch (Throwable throwable) {
            return null;
         }

         return new GameProfile((UUID)(entry), jsonObject.get("name").getAsString());
      } else {
         return null;
      }
   }

   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      if (this.getDataRef() != null) {
         jsonObject.addProperty("uuid", ((GameProfile)this.getDataRef()).getId() == null ? "" : ((GameProfile)this.getDataRef()).getId().toString());
         jsonObject.addProperty("name", ((GameProfile)this.getDataRef()).getName());
         super.applyJsonObject(jsonObject);
         jsonObject.addProperty("level", this.count);
         jsonObject.addProperty("bypassesPlayerLimit", this.enabled);
      }
   }

   public TargetDirector(JsonObject jsonObject) {
      super(resolveGameProfile(jsonObject), jsonObject);
      this.count = jsonObject.has("level") ? jsonObject.get("level").getAsInt() : 0;
      this.enabled = jsonObject.has("bypassesPlayerLimit") && jsonObject.get("bypassesPlayerLimit").getAsBoolean();
   }

   public TargetDirector(GameProfile gameProfile, int count2, boolean flag) {
      super(gameProfile);
      this.count = count2;
      this.enabled = flag;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCount() {
      return this.count;
   }
}
