package client.util;

import client.setting.ActiveOptionType;
import java.util.HashMap;

public abstract class StandardHashMapEntry {
   public HashMap hashMap;

   public boolean isActive() {
      return this.hashMap != null;
   }

   public void applyLocalFloatHolder(LocalFloatHolder localFloatHolder) {
      this.updateState();
      this.hashMap.put(localFloatHolder.getActiveOptionType(), localFloatHolder);
   }

   public HashMap getHashMap() {
      this.updateState();
      return this.hashMap;
   }

   public void applyActiveOptionType(ActiveOptionType activeOptionType) {
      if (this.isActive()) {
         this.hashMap.remove(activeOptionType);
      }
   }

   public boolean matchesActiveOptionType(ActiveOptionType activeOptionType) {
      return this.isActive() && this.hashMap.containsKey(activeOptionType);
   }

   private void updateState() {
      if (this.hashMap == null) {
         this.hashMap = new HashMap();
      }
   }

   public LocalFloatHolder resolveLocalFloatHolder(ActiveOptionType activeOptionType) {
      return this.matchesActiveOptionType(activeOptionType) ? (LocalFloatHolder)this.hashMap.get(activeOptionType) : new LocalFloatHolder(activeOptionType);
   }
}
