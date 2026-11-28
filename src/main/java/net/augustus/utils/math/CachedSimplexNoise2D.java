package net.augustus.utils.math;

import java.util.HashMap;

public class CachedSimplexNoise2D {
   public HashMap cache;

   public float sample(float f, float f2) {
      if (this.cache != null) {
         String str = f + "_" + f2;
         if (this.cache.containsKey(str)) {
            return (Float)this.cache.get(str);
         }

         float f3 = SimplexNoise.noise(f, f2);
         this.cache.put(str, f3);
      }

      return SimplexNoise.noise(f, f2);
   }
}
