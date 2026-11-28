package net.minecraft.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityGiantZombie extends EntityMob {
   public EntityGiantZombie(World world) {
      super(world);
      this.setSize(this.width * 6.0F, this.height * 6.0F);
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0);
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return this.entityWorld.getLightBrightness(pos.getX(), pos.getY(), pos.getZ()) - 0.5F;
   }

   @Override
   public float getEyeHeight() {
      return 10.440001F;
   }
}
