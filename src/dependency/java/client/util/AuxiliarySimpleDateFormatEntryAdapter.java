package client.util;

import com.google.gson.JsonObject;
import java.util.Date;

public class AuxiliarySimpleDateFormatEntryAdapter extends SimpleDateFormatEntry {
   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      if (this.getDataRef() != null) {
         jsonObject.addProperty("ip", (String)this.getDataRef());
         super.applyJsonObject(jsonObject);
      }
   }

   public AuxiliarySimpleDateFormatEntryAdapter(JsonObject jsonObject) {
      super(resolveText(jsonObject), jsonObject);
   }

   private static String resolveText(JsonObject jsonObject) {
      return jsonObject.has("ip") ? jsonObject.get("ip").getAsString() : null;
   }

   public AuxiliarySimpleDateFormatEntryAdapter(String text) {
      this(text, (Date)null, (String)null, (Date)null, (String)null);
   }

   public AuxiliarySimpleDateFormatEntryAdapter(String text, Date date, String text2, Date date2, String text3) {
      super(text, date, text2, date2, text3);
   }
}
