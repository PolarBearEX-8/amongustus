package net.minecraft.item;

import client.util.AuxiliaryUUIDEntry;
import client.util.DampenerChecker;
import client.util.RotationPool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class SimpleItemEntry extends RotationPool {
   public final EntityMob entityMob;
   public final float factor;
   public float factor2;
   public boolean enabled;
   public int count2;
   public int count3;

   public SimpleItemEntry(EntityMob entityMob2, float factor3) {
      this.entityMob = entityMob2;
      this.factor = factor3;
      this.setCount(7);
   }

   public boolean isActive3() {
      return !this.isEnabled() && this.factor2 > this.factor * 0.3F;
   }

   public void updateState4() {
      this.enabled = true;
      this.count2 = 0;
      this.count3 = this.entityMob.getRandom().nextInt(841) + 140;
   }

   private boolean matchesBlock(Block block) {
      return block instanceof BlockRedSandstoneStairs || block instanceof ExtraBlockAdapter;
   }

   @Override
   public void updateState3() {
      this.factor2 = 0.0F;
   }

   @Override
   public boolean isActive() {
      return this.entityMob.canExecute()
         && this.entityMob.entity != null
         && this.entityMob.entity instanceof EntityPlayer
         && (this.enabled || this.entityMob.isActive27());
   }

   @Override
   public void updateState() {
      this.enabled = false;
      this.factor2 = 0.0F;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public void updateState2() {
      EntityPlayer entity = (EntityPlayer)this.entityMob.entity;
      AuxiliaryUUIDEntry entityMob2 = (AuxiliaryUUIDEntry)this.entityMob;
      float factor3 = MathHelper.sqrt_float(entity.rotationYaw - this.entityMob.rotationYaw) * 0.5F;
      if (factor3 > 5.0F) {
         factor3 = 5.0F;
      }

      if (factor3 < -5.0F) {
         factor3 = -5.0F;
      }

      this.entityMob.rotationYaw = MathHelper.sqrt_float(this.entityMob.rotationYaw + factor3);
      if (this.factor2 < this.factor) {
         this.factor2 = this.factor2 + (this.factor - this.factor2) * 0.01F;
      }

      if (this.factor2 > this.factor) {
         this.factor2 = this.factor;
      }

      int count = MathHelper.ceiling_double_int(this.entityMob.posX);
      int count4 = MathHelper.ceiling_double_int(this.entityMob.posY);
      int count5 = MathHelper.ceiling_double_int(this.entityMob.posZ);
      float factor4 = this.factor2;
      if (this.enabled) {
         if (this.count2++ > this.count3) {
            this.enabled = false;
         }

         factor4 += factor4 * 1.15F * MathHelper.abs((float)this.count2 / (float)this.count3 * (float) Math.PI);
      }

      float factor5 = 0.91F;
      if (this.entityMob.onGround) {
         factor5 = this.entityMob
               .worldObj
               .getBlockState(new BlockPos(MathHelper.floor_float((float)count), MathHelper.floor_float((float)count4) - 1, MathHelper.floor_float((float)count5)))
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      float factor6 = 0.16277136F / (factor5 * factor5 * factor5);
      float factor7 = MathHelper.abs(entityMob2.rotationYaw * (float) Math.PI / 180.0F);
      float factor8 = MathHelper.cos(entityMob2.rotationYaw * (float) Math.PI / 180.0F);
      float factor9 = entityMob2.getScaleFactor6() * factor6;
      float factor10 = Math.max(factor4, 1.0F);
      float factor11 = factor9 / factor10;
      float factor12 = factor4 * factor11;
      float factor13 = -(factor12 * factor7);
      float factor14 = factor12 * factor8;
      if (MathHelper.computeFactor(factor13) > MathHelper.computeFactor(factor14)) {
         if (factor13 < 0.0F) {
            factor13 -= this.entityMob.width / 2.0F;
         }

         if (factor13 > 0.0F) {
            factor13 += this.entityMob.width / 2.0F;
         }

         factor14 = 0.0F;
      } else {
         factor13 = 0.0F;
         if (factor14 < 0.0F) {
            factor14 -= this.entityMob.width / 2.0F;
         }

         if (factor14 > 0.0F) {
            factor14 += this.entityMob.width / 2.0F;
         }
      }

      int count6 = MathHelper.ceiling_double_int(this.entityMob.posX + (double)factor13);
      int count7 = MathHelper.ceiling_double_int(this.entityMob.posZ + (double)factor14);
      int count8 = MathHelper.floor_float(this.entityMob.width + 1.0F);
      int count9 = MathHelper.floor_float(this.entityMob.height + entity.height + 1.0F);
      int count10 = MathHelper.floor_float(this.entityMob.width + 1.0F);
      if (count != count6 || count5 != count7) {
         Block block = this.entityMob.worldObj.getBlockState(new BlockPos(count, count4, count5)).getBlock();
         if (!this.matchesBlock(block)
            && (
               block.getMaterial() != Material.coreItemEntry
                  || !this.matchesBlock(this.entityMob.worldObj.getBlockState(new BlockPos(count, count4 - 1, count5)).getBlock())
            )
            && 0 == DampenerChecker.computeCount(this.entityMob.worldObj, this.entityMob, count6, count4, count7, count8, count9, count10, false, false, true)
            && 1 == DampenerChecker.computeCount(this.entityMob.worldObj, this.entityMob, count, count4 + 1, count5, count8, count9, count10, false, false, true)
            && 1 == DampenerChecker.computeCount(this.entityMob.worldObj, this.entityMob, count6, count4 + 1, count7, count8, count9, count10, false, false, true)) {
            entityMob2.getPrimaryItemEntry().updateState();
         }
      }

      if (!entity.invulnerableManager.enabled2 && this.factor2 >= this.factor * 0.5F && this.entityMob.getRandom().nextFloat() < 0.006F && !this.enabled) {
         ItemStack stack = entity.getItemStack();
         if (stack != null && stack.getItem() == Items.carrotOnAStick) {
            stack.applyEntityLivingBaseByEntityLivingBase(1, entity);
            if (stack.animationsToGo == 0) {
               ItemStack stack2 = new ItemStack(Items.fishingRod);
               stack2.processAction(stack.getStackTagCompound());
               entity.inventoryPlayer.mainInventory[entity.inventoryPlayer.currentItem] = stack2;
            }
         }
      }

      this.entityMob.applyFloat2(0.0F, factor4);
   }
}
