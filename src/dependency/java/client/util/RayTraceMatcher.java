package client.util;

import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

public class RayTraceMatcher {
   public static final int[] countArray = new int[Type.values().length];

   static {
      try {
         countArray[Type.SKIN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[Type.CAPE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
