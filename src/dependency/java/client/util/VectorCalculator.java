package client.util;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.MovementNumberProperty;
import net.minecraft.block.BlockBreakState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpecialBlockAdapter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class VectorCalculator extends EntityMinecart {
   public int count14 = -1;

   @Override
   public boolean matchesBlockBreakState(BlockBreakState blockBreakState, World world, BlockPos blockPos, IBlockState blockState, float factor) {
      return this.isActive16() && (SpecialBlockAdapter.matchesIBlockState(blockState) || SpecialBlockAdapter.matchesWorld3(world, blockPos.up()))
         ? false
         : super.matchesBlockBreakState(blockBreakState, world, blockPos, blockState, factor);
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      double offset = this.motionX * this.motionX + this.motionZ * this.motionZ;
      if (!damageSource.isEnabled5() && this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         this.resolveEntityItem(new ItemStack(Blocks.block135, 1), 0.0F);
      }

      if (damageSource.isEnabled6() || damageSource.isEnabled5() || offset >= 0.01F) {
         this.applyDouble(offset);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("TNTFuse", 99)) {
         this.count14 = tagCompound.computeCount("TNTFuse");
      }
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.TNT;
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      if (factor >= 3.0F) {
         float factor3 = factor / 10.0F;
         this.applyDouble((double)(factor3 * factor3));
      }

      super.processFloat2(factor, factor2);
   }

   public int getCount14() {
      return this.count14;
   }

   public void updateState2() {
      this.count14 = 80;
      if (!this.worldObj.state) {
         this.worldObj.applyEntity(this, (byte)10);
         if (!this.isActive7()) {
            this.worldObj.applyEntityByEntity(this, "game.tnt.primed", 1.0F, 1.0F);
         }
      }
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 10) {
         this.updateState2();
      } else {
         super.processByte(byteValue);
      }
   }

   public boolean isActive16() {
      return this.count14 > -1;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, boolean flag) {
      if (flag && this.count14 < 0) {
         this.updateState2();
      }
   }

   @Override
   public IBlockState getIBlockState() {
      return Blocks.block135.getDefaultState();
   }

   @Override
   public float computeFactor(BlockBreakState blockBreakState, World world, BlockPos blockPos, IBlockState blockState) {
      return this.isActive16() && (SpecialBlockAdapter.matchesIBlockState(blockState) || SpecialBlockAdapter.matchesWorld3(world, blockPos.up()))
         ? 0.0F
         : super.computeFactor(blockBreakState, world, blockPos, blockState);
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      Entity entityAsEntity = damageSource.getEntityAsEntity();
      if (entityAsEntity instanceof EntityArrow) {
         EntityArrow entityArrow = (EntityArrow)entityAsEntity;
         if (entityArrow.canExecute2()) {
            this.applyDouble(entityArrow.motionX * entityArrow.motionX + entityArrow.motionY * entityArrow.motionY + entityArrow.motionZ * entityArrow.motionZ);
         }
      }

      return super.matchesDamageSource(damageSource, factor);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("TNTFuse", this.count14);
   }

   public void applyDouble(double offset) {
      if (!this.worldObj.state) {
         double offset2 = Math.sqrt(offset);
         if (offset2 > 5.0) {
            offset2 = 5.0;
         }

         this.worldObj
            .resolveBlockBreakStateByEntity(this, this.posX, this.posY, this.posZ, (float)(4.0 + this.random.nextDouble() * 1.5 * offset2), true);
         this.updateState9();
      }
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.count14 > 0) {
         this.count14--;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0, new int[0]
            );
      } else if (this.count14 == 0) {
         this.applyDouble(this.motionX * this.motionX + this.motionZ * this.motionZ);
      }

      if (this.isCollidedHorizontally) {
         double offset = this.motionX * this.motionX + this.motionZ * this.motionZ;
         if (offset >= 0.01F) {
            this.applyDouble(offset);
         }
      }
   }

   public VectorCalculator(World world) {
      super(world);
   }

   public VectorCalculator(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
