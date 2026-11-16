package net.minecraft.entity;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityGiant extends EntityMonster {
   @Override
   public float computeFactor(BlockPos blockPos) {
      return this.worldObj.computeFactor(blockPos.getX(), blockPos.getY(), blockPos.getZ()) - 0.5F;
   }

   @Override
   public float getHeight2() {
      return 10.440001F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(100.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.5);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(50.0);
   }

   public EntityGiant(World world) {
      super(world);
      this.applyFloat(this.width * 6.0F, this.height * 6.0F);
   }
}
