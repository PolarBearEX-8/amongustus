package client.util;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.UUID;

public class SimpleDateFormatEntryAdapter extends SimpleDateFormatEntry {
   public SimpleDateFormatEntryAdapter(GameProfile gameProfile, Date date, String text, Date date2, String text2) {
      super(gameProfile, date2, text, date2, text2);
   }

   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      if (this.getDataRef() != null) {
         jsonObject.addProperty("uuid", ((GameProfile)this.getDataRef()).getId() == null ? "" : ((GameProfile)this.getDataRef()).getId().toString());
         jsonObject.addProperty("name", ((GameProfile)this.getDataRef()).getName());
         super.applyJsonObject(jsonObject);
      }
   }

   public SimpleDateFormatEntryAdapter(JsonObject jsonObject) {
      super(resolveGameProfile(jsonObject), jsonObject);
   }

   public SimpleDateFormatEntryAdapter(GameProfile gameProfile) {
      this(gameProfile, (Date)null, (String)null, (Date)null, (String)null);
   }

   private static GameProfile resolveGameProfile(JsonObject jsonObject) {
        UUID entry = null;
      if (jsonObject.has("uuid") && jsonObject.has("name")) {
         String asString = jsonObject.get("uuid").getAsString();

         try {
            entry = UUID.fromString(asString);
         } catch (Throwable throwable) {
            return null;
         }

         return new GameProfile(entry, jsonObject.get("name").getAsString());
      } else {
         return null;
      }
   }
}
