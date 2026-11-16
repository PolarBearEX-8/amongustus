package client.util;

import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class SimpleDateFormatEntry extends TargetStack {
   public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   public final Date date;
   public final String labelText;
   public final Date date2;
   public final String labelText2;

   public Date getDate2() {
      return this.date2;
   }

   public SimpleDateFormatEntry(Object entry, JsonObject jsonObject) {
      super(entry, jsonObject);

      Date date3;
      try {
         date3 = jsonObject.has("created") ? simpleDateFormat.parse(jsonObject.get("created").getAsString()) : new Date();
      } catch (ParseException parseException) {
         date3 = new Date();
      }

      this.date = date3;
      this.labelText = jsonObject.has("source") ? jsonObject.get("source").getAsString() : "(Unknown)";

      Date date4;
      try {
         date4 = jsonObject.has("expires") ? simpleDateFormat.parse(jsonObject.get("expires").getAsString()) : null;
      } catch (ParseException parseException2) {
         date4 = null;
      }

      this.date2 = date4;
      this.labelText2 = jsonObject.has("reason") ? jsonObject.get("reason").getAsString() : "Banned by an operator.";
   }

   @Override
   public boolean before() {
      return this.date2 == null ? false : this.date2.before(new Date());
   }

   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      jsonObject.addProperty("created", simpleDateFormat.format(this.date));
      jsonObject.addProperty("source", this.labelText);
      jsonObject.addProperty("expires", this.date2 == null ? "forever" : simpleDateFormat.format(this.date2));
      jsonObject.addProperty("reason", this.labelText2);
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public SimpleDateFormatEntry(Object entry, Date date3, String text, Date date4, String text2) {
      super(entry);
      this.date = date3 == null ? new Date() : date3;
      this.labelText = text == null ? "(Unknown)" : text;
      this.date2 = date4;
      this.labelText2 = text2 == null ? "Banned by an operator." : text2;
   }
}
