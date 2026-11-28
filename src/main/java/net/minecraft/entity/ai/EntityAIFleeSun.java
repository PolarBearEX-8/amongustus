package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAIFleeSun extends EntityAIBase {
   private EntityCreature theCreature;
   private double shelterX;
   private double shelterY;
   private double shelterZ;
   private double movementSpeed;
   private World theWorld;

   @Override
   public boolean continueExecuting() {
      return !this.theCreature.getNavigator().noPath();
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theWorld.isDaytime()) {
         return false;
      } else if (!this.theCreature.isBurning()) {
         return false;
      } else if (!this.theWorld
         .canSeeSky(
            MathHelper.floor_double(this.theCreature.posX),
            MathHelper.floor_double(this.theCreature.getEntityBoundingBox().minY),
            MathHelper.floor_double(this.theCreature.posZ)
         )) {
         return false;
      } else {
         Vec3 vec = this.findPossibleShelter();
         if (vec == null) {
            return false;
         } else {
            this.shelterX = vec.xCoord;
            this.shelterY = vec.yCoord;
            this.shelterZ = vec.zCoord;
            return true;
         }
      }
   }

   public EntityAIFleeSun(EntityCreature entityCreature, double d) {
      this.theCreature = entityCreature;
      this.movementSpeed = d;
      this.theWorld = entityCreature.entityWorld;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.theCreature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
   }

   private Vec3 findPossibleShelter() {
      Random rng = this.theCreature.getRNG();
      BlockPos pos = new BlockPos(this.theCreature.posX, this.theCreature.getEntityBoundingBox().minY, this.theCreature.posZ);

      for (int i = 0; i < 10; i++) {
         BlockPos pos2 = pos.add(rng.nextInt(20) - 10, rng.nextInt(6) - 3, rng.nextInt(20) - 10);
         if (!this.theWorld.canSeeSky(pos2.getX(), pos2.getY(), pos2.getZ()) && this.theCreature.getBlockPathWeight(pos2) < 0.0F) {
            return new Vec3((double)pos2.getX(), (double)pos2.getY(), (double)pos2.getZ());
         }
      }

      return null;
   }
}
