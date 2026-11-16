package net.minecraft.entity;

import client.util.EndManager;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityEndercrystal extends Entity {
   public int count12;
   public int count11;

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         if (!this.isDead && !this.worldObj.state) {
            this.count11 = 0;
            if (this.count11 <= 0) {
               this.updateState9();
               if (!this.worldObj.state) {
                  this.worldObj.resolveBlockBreakStateByEntity((Entity)null, this.posX, this.posY, this.posZ, 6.0F, true);
               }
            }
         }

         return true;
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public boolean isActive5() {
      return true;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.count12++;
      this.dataWatcher.applyTargetByTarget(8, this.count11);
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.posY);
      int count3 = MathHelper.ceiling_double_int(this.posZ);
      if (this.worldObj.secondaryFloatArrayEntry instanceof EndManager && this.worldObj.getBlockState(new BlockPos(count, count2, count3)).getBlock() != Blocks.blockFire) {
         this.worldObj.matchesBlockPos(new BlockPos(count, count2, count3), Blocks.blockFire.getDefaultState());
      }
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyTarget(8, this.count11);
   }

   public EntityEndercrystal(World world) {
      super(world);
      this.enabled = true;
      this.applyFloat(2.0F, 2.0F);
      this.count11 = 5;
      this.count12 = this.random.nextInt(100000);
   }

   public EntityEndercrystal(World world, double posX, double posY, double posZ) {
      this(world);
      this.processDouble2(posX, posY, posZ);
   }
}
