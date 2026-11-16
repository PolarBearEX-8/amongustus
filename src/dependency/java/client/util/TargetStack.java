package client.util;

import com.google.gson.JsonObject;

public class TargetStack {
   public final Object dataRef;

   public void applyJsonObject(JsonObject jsonObject) {
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public boolean before() {
      return false;
   }

   public TargetStack(Object entry) {
      this.dataRef = entry;
   }

   public TargetStack(Object entry, JsonObject jsonObject) {
      this.dataRef = entry;
   }
}
