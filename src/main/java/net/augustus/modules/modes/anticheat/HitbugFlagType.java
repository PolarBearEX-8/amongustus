package net.augustus.modules.modes.anticheat;

public enum HitbugFlagType {
   HT_LIGHT("HitLight"),
   BLOCKING("BLOCKING"),
   CANCEL("cancel"),
   HT_MEDIUM("HitMedium"),
   GARBAGE_HIT("GarbageHit"),
   CANCEL_FIRST("CancelFist"),

   DMG_MEDIUM("HitMedium"),
   DM_LIGHT("DMGLight");

   private final String label;

   private HitbugFlagType(String str) {
      this.label = str;
   }

   public String getLabel() {
      return this.label;
   }
}
