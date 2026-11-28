package net.minecraft.server.management;

import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BanEntry extends UserListEntry {
   public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   public final Date banStartDate;
   public final String bannedBy;
   public final Date banEndDate;
   public final String reason;

   public BanEntry(Object obj, JsonObject json) {
      super(obj, json);

      Date date;
      try {
         date = json.has("created") ? dateFormat.parse(json.get("created").getAsString()) : new Date();
      } catch (ParseException e) {
         date = new Date();
      }

      this.banStartDate = date;
      this.bannedBy = json.has("source") ? json.get("source").getAsString() : "(Unknown)";

      Date date2;
      try {
         date2 = json.has("expires") ? dateFormat.parse(json.get("expires").getAsString()) : null;
      } catch (ParseException e2) {
         date2 = null;
      }

      this.banEndDate = date2;
      this.reason = json.has("reason") ? json.get("reason").getAsString() : "Banned by an operator.";
   }

   public String getBanReason() {
      return this.reason;
   }

   public Date getBanEndDate() {
      return this.banEndDate;
   }

   public BanEntry(Object obj, Date date, String str, Date date2, String str2) {
      super(obj);
      this.banStartDate = date == null ? new Date() : date;
      this.bannedBy = str == null ? "(Unknown)" : str;
      this.banEndDate = date2;
      this.reason = str2 == null ? "Banned by an operator." : str2;
   }

   @Override
   public boolean hasBanExpired() {
      return this.banEndDate == null ? false : this.banEndDate.before(new Date());
   }

   @Override
   public void onSerialization(JsonObject json) {
      json.addProperty("created", dateFormat.format(this.banStartDate));
      json.addProperty("source", this.bannedBy);
      json.addProperty("expires", this.banEndDate == null ? "forever" : dateFormat.format(this.banEndDate));
      json.addProperty("reason", this.reason);
   }
}
