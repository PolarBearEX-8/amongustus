package net.augustus.cosmetics.molang;

public class MolangUtils {
   public static float booleanToFloat(boolean flag) {
      return flag ? 1.0F : 0.0F;
   }

   public static float normalizeTime(long l) {
      return (float)l / 24000.0F;
   }
}
