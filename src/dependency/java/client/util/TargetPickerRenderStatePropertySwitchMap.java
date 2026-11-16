package client.util;

import client.setting.RenderStateProperty;

public class TargetPickerRenderStatePropertySwitchMap {
   public static final int[] countArray = new int[RenderStateProperty.values().length];

   static {
      try {
         countArray[RenderStateProperty.TEXTURE_1D.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[RenderStateProperty.TEXTURE_2D.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[RenderStateProperty.TEXTURE_3D.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[RenderStateProperty.TEXTURE_RECTANGLE.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
