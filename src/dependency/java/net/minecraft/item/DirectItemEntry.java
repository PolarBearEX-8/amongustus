package net.minecraft.item;

import client.util.AuxiliaryUUIDEntry;
import client.util.RotationPool;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class DirectItemEntry extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value;
   public double value2;
   public double value3;
   public double value4;
   public World world;

   private Vec3 getVec3() {
      Random random = this.auxiliaryUUIDEntry.getRandom();
      BlockPos blockPos = new BlockPos(this.auxiliaryUUIDEntry.posX, this.auxiliaryUUIDEntry.getBoundingBox2().minY, this.auxiliaryUUIDEntry.posZ);

      for (int index = 0; index < 10; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
         if (!this.world.checkIndex2(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()) && this.auxiliaryUUIDEntry.computeFactor(blockPos2) < 0.0F) {
            return new Vec3((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
         }
      }

      return null;
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.auxiliaryUUIDEntry.getPathfindManager().isActive2();
   }

   @Override
   public boolean isActive() {
      if (!this.world.isActive2()) {
         return false;
      } else if (!this.auxiliaryUUIDEntry.canExecute2()) {
         return false;
      } else if (!this.world
         .checkIndex2(
            MathHelper.ceiling_double_int(this.auxiliaryUUIDEntry.posX),
            MathHelper.ceiling_double_int(this.auxiliaryUUIDEntry.getBoundingBox2().minY),
            MathHelper.ceiling_double_int(this.auxiliaryUUIDEntry.posZ)
         )) {
         return false;
      } else {
         Vec3 vec3 = this.getVec3();
         if (vec3 == null) {
            return false;
         } else {
            this.value = vec3.xCoord;
            this.value2 = vec3.yCoord;
            this.value3 = vec3.zCoord;
            return true;
         }
      }
   }

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.getPathfindManager().checkDouble(this.value, this.value2, this.value3, this.value4);
   }

   public DirectItemEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value4 = offset;
      this.world = auxiliaryUUIDEntry2.worldObj;
      this.setCount(1);
   }
}
