package net.optifine;

import net.minecraft.util.Vec3;
import net.optifine.config.Config;

public class CustomColorFader {
   private Vec3 color = null;
   private long timeUpdate = System.currentTimeMillis();

   public Vec3 getColor(double d, double d2, double d3) {
      if (this.color == null) {
         this.color = new Vec3(d, d2, d3);
         return this.color;
      } else {
         long l = System.currentTimeMillis();
         long l2 = l - this.timeUpdate;
         if (l2 == 0L) {
            return this.color;
         } else {
            this.timeUpdate = l;
            if (Math.abs(d - this.color.xCoord) < 0.004 && Math.abs(d2 - this.color.yCoord) < 0.004 && Math.abs(d3 - this.color.zCoord) < 0.004) {
               return this.color;
            } else {
               double d4 = (double)l2 * 0.001;
               d4 = Config.limit2(d4, 0.0, 1.0);
               double d5 = d - this.color.xCoord;
               double d6 = d2 - this.color.yCoord;
               double d7 = d3 - this.color.zCoord;
               double d8 = this.color.xCoord + d5 * d4;
               double d9 = this.color.yCoord + d6 * d4;
               double d10 = this.color.zCoord + d7 * d4;
               this.color = new Vec3(d8, d9, d10);
               return this.color;
            }
         }
      }
   }
}
