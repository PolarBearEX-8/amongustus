package net.minecraft.server.management;

import com.google.gson.JsonObject;

public class UserListEntry {
   private final Object value;

   public void onSerialization(JsonObject json) {
   }

   public UserListEntry(Object obj) {
      this.value = obj;
   }

   public UserListEntry(Object obj, JsonObject json) {
      this.value = obj;
   }

   public Object getValue() {
      return this.value;
   }

   public boolean hasBanExpired() {
      return false;
   }
}
