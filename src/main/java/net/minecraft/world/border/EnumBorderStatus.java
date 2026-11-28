package net.minecraft.world.border;

public enum EnumBorderStatus {
   GROWING(4259712),
   SHRINKING(16724016),
   STATIONARY(2138367);

private final int id;

   public int getID() {
      return this.id;
   }

   private EnumBorderStatus(int n) {
      this.id = n;
   }
}
