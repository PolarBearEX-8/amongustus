package client.util;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class TargetSystem extends TargetStack {
   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      if (this.getDataRef() != null) {
         jsonObject.addProperty("uuid", ((GameProfile)this.getDataRef()).getId() == null ? "" : ((GameProfile)this.getDataRef()).getId().toString());
         jsonObject.addProperty("name", ((GameProfile)this.getDataRef()).getName());
         super.applyJsonObject(jsonObject);
      }
   }

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

   public TargetSystem(GameProfile gameProfile) {
      super(gameProfile);
   }

   public TargetSystem(JsonObject jsonObject) {
      super(resolveGameProfile(jsonObject), jsonObject);
   }
}
