package net.augustus.cosmetics.model;

public enum CosmeticAnimationTrigger {
   IDLE("idle", 0),
   MOVING("moving", 1),
   SNEAKING("sneaking", 2);

   public static final CosmeticAnimationTrigger[] VALUES = values();
   private int id;
   private String animationName;

   private CosmeticAnimationTrigger(String str, int n) {
      this.animationName = str;
      this.id = n;
   }

   public String getAnimationName() {
      return this.animationName;
   }

   public int getId() {
      return this.id;
   }
}
