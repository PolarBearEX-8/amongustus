package net.minecraft.util;

public enum EnumWorldBlockLayer {
   SOLID("Solid"),
   CUTOUT_MIPPED("Mipped Cutout"),
   CUTOUT("Cutout"),
   TRANSLUCENT("Translucent");

private final String layerName;

   @Override
   public String toString() {
      return this.layerName;
   }

   private EnumWorldBlockLayer(String str) {
      this.layerName = str;
   }
}
