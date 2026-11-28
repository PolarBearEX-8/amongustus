package net.optifine.config;

import net.minecraft.world.World;

public enum Weather {
   CLEAR,
   RAIN,
   THUNDER;

public static Weather getWeather(World world, float f) {
      if (world.getThunderStrength(f) > 0.5F) {
         return THUNDER;
      } else {
         return world.getRainStrength(f) > 0.5F ? RAIN : CLEAR;
      }
   }
}
