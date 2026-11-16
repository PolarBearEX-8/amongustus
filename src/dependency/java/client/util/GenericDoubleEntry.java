package client.util;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class GenericDoubleEntry {
   public double value;
   public double value4;
   public boolean enabled;
   public Vec3 vec3;
   public Vec3 vec2;
   public int count;
   public boolean enabled2 = false;
   public double value3;
   public double value2;

   public GenericDoubleEntry(Vec3 vec, Vec3 vec4, double posX, double posX2, double posY, double posZ, int count2) {
      this.vec2 = vec;
      this.vec3 = vec4;
      this.value2 = posX;
      this.value = posX2;
      this.value3 = MathHelper.getDouble4(posY);
      this.value4 = posZ;
      this.count = count2;
   }
}
