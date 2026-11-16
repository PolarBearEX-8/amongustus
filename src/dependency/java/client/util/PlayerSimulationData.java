package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class PlayerSimulationData {
   public double value;
   public double value2;
   public double value3;
   public double value4;
   public double value5;
   public double value6;
   public double value7;
   public int count = 0;
   public EntityLivingBase targetEntity;
   public double value8;
   public double value9;

   public Vec3 getVecAsVec3() {
      return new Vec3(this.value, this.value3, this.value7);
   }

   public void applyDouble(double offset) {
      this.count = this.targetEntity.ticksExisted;
      double offset2 = 1.0;
      Vec3 vec3 = this.targetEntity.getVec32(1.0F);
      vec3.getVec311(this.targetEntity.getVec32(0.0F)).getVec3(offset2);
      Vec3 vec32 = this.targetEntity.getVec32(0.0F);
      double offset3 = (this.value6 - vec32.xCoord) / (vec3.xCoord - vec32.xCoord);
      double offset4 = (this.value4 - vec32.yCoord) / (vec3.yCoord - vec32.yCoord);
      double offset5 = (this.value9 - vec32.zCoord) / (vec3.zCoord - vec32.zCoord);
      if (!Double.isFinite(offset3)) {
         offset3 = 0.0;
      }

      if (!Double.isFinite(offset4)) {
         offset4 = 0.0;
      }

      if (!Double.isFinite(offset5)) {
         offset5 = 0.0;
      }

      double offset6 = MathHelper.getDouble7(Math.max(Math.max(Math.max(offset3, offset4), offset5), -0.5), -1.0, 1.0) + Math.random() * 0.1;
      double offset7 = Math.max(offset, offset6);
      this.value = this.value6;
      this.value3 = this.value4;
      this.value7 = this.value9;
      this.value6 = MathHelper.getDouble7(vec32.xCoord + (vec3.xCoord - vec32.xCoord) * offset7, vec3.xCoord - 1.0, vec3.xCoord + 1.0);
      this.value4 = MathHelper.getDouble7(vec32.yCoord + (vec3.yCoord - vec32.yCoord) * offset7, vec3.yCoord - 1.0, vec3.yCoord + 1.0);
      this.value9 = MathHelper.getDouble7(vec32.zCoord + (vec3.zCoord - vec32.zCoord) * offset7, vec3.zCoord - 1.0, vec3.zCoord + 1.0);
      this.value = vec3.xCoord;
      this.value3 = vec3.yCoord;
      this.value7 = vec3.zCoord;
   }

   public PlayerSimulationData(EntityLivingBase livingEntity) {
      this.targetEntity = livingEntity;
      this.value6 = livingEntity.posX;
      this.value4 = livingEntity.posY;
      this.value9 = livingEntity.posZ;
   }

   public Vec3 getVec3() {
      return new Vec3(this.value6, this.value4, this.value9);
   }
}
