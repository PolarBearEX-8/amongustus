package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class EntityAIControlledByPlayer extends EntityAIBase {
   private final EntityLiving thisEntity;
   private final float maxSpeed;
   private float currentSpeed;
   private boolean speedBoosted;
   private int speedBoostTime;
   private int maxSpeedBoostTime;

   @Override
   public void updateTask() {
      EntityPlayer riddenByEntity = (EntityPlayer)this.thisEntity.riddenByEntity;
      EntityCreature thisEntity2 = (EntityCreature)this.thisEntity;
      float f = MathHelper.wrapAngleTo180_float(riddenByEntity.rotationYaw - this.thisEntity.rotationYaw) * 0.5F;
      if (f > 5.0F) {
         f = 5.0F;
      }

      if (f < -5.0F) {
         f = -5.0F;
      }

      this.thisEntity.rotationYaw = MathHelper.wrapAngleTo180_float(this.thisEntity.rotationYaw + f);
      if (this.currentSpeed < this.maxSpeed) {
         this.currentSpeed = this.currentSpeed + (this.maxSpeed - this.currentSpeed) * 0.01F;
      }

      if (this.currentSpeed > this.maxSpeed) {
         this.currentSpeed = this.maxSpeed;
      }

      int n = MathHelper.floor_double(this.thisEntity.posX);
      int n2 = MathHelper.floor_double(this.thisEntity.posY);
      int n3 = MathHelper.floor_double(this.thisEntity.posZ);
      float currentSpeed2 = this.currentSpeed;
      if (this.speedBoosted) {
         if (this.speedBoostTime++ > this.maxSpeedBoostTime) {
            this.speedBoosted = false;
         }

         currentSpeed2 += currentSpeed2 * 1.15F * MathHelper.sin((float)this.speedBoostTime / (float)this.maxSpeedBoostTime * (float) Math.PI);
      }

      float f2 = 0.91F;
      if (this.thisEntity.onGround) {
         f2 = this.thisEntity
               .entityWorld
               .getBlockState(new BlockPos(MathHelper.floor_float((float)n), MathHelper.floor_float((float)n2) - 1, MathHelper.floor_float((float)n3)))
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      float f3 = 0.16277136F / (f2 * f2 * f2);
      float f4 = MathHelper.sin(thisEntity2.rotationYaw * (float) Math.PI / 180.0F);
      float f5 = MathHelper.cos(thisEntity2.rotationYaw * (float) Math.PI / 180.0F);
      float f6 = thisEntity2.getAIMoveSpeed() * f3;
      float f7 = Math.max(currentSpeed2, 1.0F);
      f7 = f6 / f7;
      float f8 = currentSpeed2 * f7;
      float f9 = -(f8 * f4);
      float f10 = f8 * f5;
      if (MathHelper.abs(f9) > MathHelper.abs(f10)) {
         if (f9 < 0.0F) {
            f9 -= this.thisEntity.width / 2.0F;
         }

         if (f9 > 0.0F) {
            f9 += this.thisEntity.width / 2.0F;
         }

         f10 = 0.0F;
      } else {
         f9 = 0.0F;
         if (f10 < 0.0F) {
            f10 -= this.thisEntity.width / 2.0F;
         }

         if (f10 > 0.0F) {
            f10 += this.thisEntity.width / 2.0F;
         }
      }

      int n4 = MathHelper.floor_double(this.thisEntity.posX + (double)f9);
      int n5 = MathHelper.floor_double(this.thisEntity.posZ + (double)f10);
      int n6 = MathHelper.floor_float(this.thisEntity.width + 1.0F);
      int n7 = MathHelper.floor_float(this.thisEntity.height + riddenByEntity.height + 1.0F);
      int n8 = MathHelper.floor_float(this.thisEntity.width + 1.0F);
      if (n != n4 || n3 != n5) {
         Block block = this.thisEntity.entityWorld.getBlockState(new BlockPos(n, n2, n3)).getBlock();
         if ((!this.isStairOrSlab(block)
            && (block.getMaterial() != Material.air || !this.isStairOrSlab(this.thisEntity.entityWorld.getBlockState(new BlockPos(n, n2 - 1, n3)).getBlock())))
            && 0 == WalkNodeProcessor.evaluateNode(this.thisEntity.entityWorld, this.thisEntity, n4, n2, n5, n6, n7, n8, false, false, true)
            && 1 == WalkNodeProcessor.evaluateNode(this.thisEntity.entityWorld, this.thisEntity, n, n2 + 1, n3, n6, n7, n8, false, false, true)
            && 1 == WalkNodeProcessor.evaluateNode(this.thisEntity.entityWorld, this.thisEntity, n4, n2 + 1, n5, n6, n7, n8, false, false, true)) {
            thisEntity2.getJumpHelper().setJumping();
         }
      }

      if (!riddenByEntity.capabilities.isCreativeMode && this.currentSpeed >= this.maxSpeed * 0.5F && this.thisEntity.getRNG().nextFloat() < 0.006F && !this.speedBoosted) {
         ItemStack heldItem = riddenByEntity.getHeldItem();
         if (heldItem != null && heldItem.getItem() == Items.carrot_on_a_stick) {
            heldItem.damageItem(1, riddenByEntity);
            if (heldItem.stackSize == 0) {
               ItemStack stack = new ItemStack(Items.fishing_rod);
               stack.setTagCompound(heldItem.getTagCompound());
               riddenByEntity.inventory.mainInventory[riddenByEntity.inventory.currentItem] = stack;
            }
         }
      }

      this.thisEntity.moveEntityWithHeading(0.0F, currentSpeed2);
   }

   public EntityAIControlledByPlayer(EntityLiving entityLiving, float f) {
      this.thisEntity = entityLiving;
      this.maxSpeed = f;
      this.setMutexBits(7);
   }

   public boolean isControlledByPlayer() {
      return !this.isSpeedBoosted() && this.currentSpeed > this.maxSpeed * 0.3F;
   }

   @Override
   public boolean shouldExecute() {
      return this.thisEntity.isEntityAlive()
         && this.thisEntity.riddenByEntity != null
         && this.thisEntity.riddenByEntity instanceof EntityPlayer
         && (this.speedBoosted || this.thisEntity.canBeSteered());
   }

   public void boostSpeed() {
      this.speedBoosted = true;
      this.speedBoostTime = 0;
      this.maxSpeedBoostTime = this.thisEntity.getRNG().nextInt(841) + 140;
   }

   private boolean isStairOrSlab(Block block) {
      return block instanceof BlockStairs || block instanceof BlockSlab;
   }

   @Override
   public void resetTask() {
      this.speedBoosted = false;
      this.currentSpeed = 0.0F;
   }

   public boolean isSpeedBoosted() {
      return this.speedBoosted;
   }

   @Override
   public void startExecuting() {
      this.currentSpeed = 0.0F;
   }
}
