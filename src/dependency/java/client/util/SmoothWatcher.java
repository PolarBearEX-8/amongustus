package client.util;

import net.minecraft.world.World;

public enum SmoothWatcher {
    CLEAR,
    RAIN,
    THUNDER;

   public static SmoothWatcher resolveSmoothWatcher(World world, float factor) {
      if (world.getFloat7(factor) > 0.5F) {
         return THUNDER;
      } else {
         return world.getFloat6(factor) > 0.5F ? RAIN : CLEAR;
      }
   }


}
