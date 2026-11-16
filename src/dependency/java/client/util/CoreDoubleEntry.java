package client.util;

import net.minecraft.util.MathHelper;

public class CoreDoubleEntry {
   public int count2;
   public double value3;
   public double value2;
   public double value;
   public double value4;

   public CoreDoubleEntry(double posX, double posX2, double posY, double posZ, int count) {
      this.value4 = posX;
      this.value3 = posX2;
      this.value = MathHelper.getDouble4(posY);
      this.value2 = posZ;
      this.count2 = count;
   }
}
