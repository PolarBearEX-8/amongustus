package net.augustus.render;

import net.augustus.utils.TimeHelper;
import org.lwjgl.opengl.GL11;

public class GpuVendorDetector {
   private static GpuVendor cachedVendor;

   public static boolean isNvidia() {
      return getVendor() == GpuVendor.NVIDIA;
   }

   public static GpuVendor getVendor() {
      if (cachedVendor == null) {
         cachedVendor = detectVendor();
      }

      return cachedVendor;
   }

   private static GpuVendor detectVendor() {
      String str = GL11.glGetString(7936);
      if (str == null) {
         return GpuVendor.UNKNOWN;
      } else {
         String str2 = str.toLowerCase();
         if (str2.contains("nvidia")) {
            return GpuVendor.NVIDIA;
         } else if (str2.contains("amd") || str2.contains("ati") || str2.contains("rx ")) {
            return GpuVendor.AMD;
         } else {
            return str2.contains("intel") ? GpuVendor.INTEL : GpuVendor.UNKNOWN;
         }
      }
   }

   public static boolean isAmd() {
      return getVendor() == GpuVendor.AMD;
   }

   static {
      cachedVendor = null;
   }
}
