package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZoglinAdapter extends TertiaryEntityAdapter {
   public Material material;
   public int count15;

   @Override
   public int computeCount(float factor) {
      return this.material == Material.coreItemEntry1 ? super.computeCount(factor) : 257;
   }

   @Override
   public float computeFactor(float factor) {
      return this.material == Material.coreItemEntry1 ? super.computeFactor(factor) : 1.0F;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.material == Material.coreItemEntry1) {
         this.factor8 = 0.2F;
         this.factor11 = 0.3F;
         this.factor5 = 1.0F;
      } else {
         this.factor8 = 1.0F;
         this.factor11 = 16.0F / (float)(40 - this.count15 + 16);
         this.factor5 = 4.0F / (float)(40 - this.count15 + 8);
      }

      this.motionY = this.motionY - (double)this.factor6;
      if (this.count15-- > 0) {
         this.motionX *= 0.02;
         this.motionY *= 0.02;
         this.motionZ *= 0.02;
         this.applyIndex5(113);
      } else {
         this.applyIndex5(112);
      }

      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.count14-- <= 0) {
         this.updateState9();
      }

      if (this.onGround) {
         if (this.material == Material.coreItemEntry1) {
            this.updateState9();
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.WATER_SPLASH, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0, new int[0]
               );
         } else {
            this.applyIndex5(114);
         }

         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }

      BlockPos blockPos = new BlockPos(this);
      IBlockState blockState = this.worldObj.getBlockState(blockPos);
      Material material2 = blockState.getBlock().getMaterial();
      if (material2.isActive2() || material2.canExecute2()) {
         double offset = 0.0;
         if (blockState.getBlock() instanceof BlockLiquid) {
            offset = (double)BlockLiquid.computeFactor((Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry));
         }

         double offset2 = (double)(MathHelper.ceiling_double_int(this.posY) + 1) - offset;
         if (this.posY < offset2) {
            this.updateState9();
         }
      }
   }

   public EntityZoglinAdapter(World world, double posX, double posY, double posZ, Material material2) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.motionX = this.motionY = this.motionZ = 0.0;
      if (material2 == Material.coreItemEntry1) {
         this.factor8 = 0.0F;
         this.factor11 = 0.0F;
         this.factor5 = 1.0F;
      } else {
         this.factor8 = 1.0F;
         this.factor11 = 0.0F;
         this.factor5 = 0.0F;
      }

      this.applyIndex5(113);
      this.applyFloat(0.01F, 0.01F);
      this.factor6 = 0.06F;
      this.material = material2;
      this.count15 = 40;
      this.count14 = (int)(64.0 / (Math.random() * 0.8 + 0.2));
      this.motionX = this.motionY = this.motionZ = 0.0;
   }
}
