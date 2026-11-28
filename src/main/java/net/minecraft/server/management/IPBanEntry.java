package net.minecraft.server.management;

import com.google.gson.JsonObject;
import java.util.Date;

public class IPBanEntry extends BanEntry {

   private static String getIPFromJson(JsonObject json) {
      return json.has("ip") ? json.get("ip").getAsString() : null;
   }

   public IPBanEntry(String str) {
      this(str, (Date)null, (String)null, (Date)null, (String)null);
   }

   @Override
   public void onSerialization(JsonObject json) {
      if (this.getValue() != null) {
         json.addProperty("ip", (String)this.getValue());
         super.onSerialization(json);
      }
   }

   public IPBanEntry(String str, Date date, String str2, Date date2, String str3) {
      super(str, date, str2, date2, str3);
   }

   public IPBanEntry(JsonObject json) {
      super(getIPFromJson(json), json);
   }
}
