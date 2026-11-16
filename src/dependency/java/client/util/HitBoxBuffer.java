package client.util;

import client.event.NativeListener;
import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class HitBoxBuffer extends UUIDEntryAdapter implements NativeListener {
   public Block block = Blocks.blockGrass;
   public int count31;
   public EntityPlayer entityPlayer2;

   @Override
   public float computeFactor(BlockPos blockPos) {
      return this.worldObj.getIBlockState(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()).getBlock() == Blocks.blockGrass
         ? 10.0F
         : this.worldObj.computeFactor(blockPos.getX(), blockPos.getY(), blockPos.getZ()) - 0.5F;
   }

   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack == null ? false : stack.getItem() == Items.wheat;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count31 = tagCompound.computeCount("InLove");
   }

   @Override
   public int nextInt(EntityPlayer player) {
      return 1 + this.worldObj.random.nextInt(3);
   }

   public void applyEntityPlayer(EntityPlayer player, ItemStack stack) {
      if (!player.invulnerableManager.enabled2) {
         stack.animationsToGo--;
         if (stack.animationsToGo <= 0) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
         }
      }
   }

   public void applyEntityPlayer(EntityPlayer player) {
      this.count31 = 600;
      this.entityPlayer2 = player;
      this.worldObj.applyEntity(this, (byte)18);
   }

   public boolean matchesHitBoxBuffer(HitBoxBuffer hitBoxBuffer) {
      return hitBoxBuffer == this ? false : (hitBoxBuffer.getClass() != this.getClass() ? false : this.isActive31() && hitBoxBuffer.isActive31());
   }

   public EntityPlayer getEntityPlayer2() {
      return this.entityPlayer2;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 18) {
         for (int index = 0; index < 7; index++) {
            double offset = this.random.nextGaussian() * 0.02;
            double offset2 = this.random.nextGaussian() * 0.02;
            double offset3 = this.random.nextGaussian() * 0.02;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.HEART,
                  this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                  this.posY + 0.5 + (double)(this.random.nextFloat() * this.height),
                  this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                  offset,
                  offset2,
                  offset3,
                  new int[0]
               );
         }
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null) {
         if (this.matchesItemStackByItemStack(slot) && this.getCount9() == 0 && this.count31 <= 0) {
            this.applyEntityPlayer(player, slot);
            this.applyEntityPlayer(player);
            return true;
         }

         if (this.isActive16() && this.matchesItemStackByItemStack(slot)) {
            this.applyEntityPlayer(player, slot);
            this.applyIndexByIndex((int)((float)(-this.getCount9() / 20) * 0.1F), true);
            return true;
         }
      }

      return super.matchesEntityPlayer6(player);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("InLove", this.count31);
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         this.count31 = 0;
         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public int getCount7() {
      return 120;
   }

   public boolean isActive31() {
      return this.count31 > 0;
   }

   @Override
   public void updateState3() {
      if (this.getCount9() != 0) {
         this.count31 = 0;
      }

      super.updateState3();
   }

   public void updateState49() {
      this.count31 = 0;
   }

   @Override
   public boolean isActive24() {
      return false;
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (this.getCount9() != 0) {
         this.count31 = 0;
      }

      if (this.count31 > 0) {
         this.count31--;
         if (this.count31 % 10 == 0) {
            double offset = this.random.nextGaussian() * 0.02;
            double offset2 = this.random.nextGaussian() * 0.02;
            double offset3 = this.random.nextGaussian() * 0.02;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.HEART,
                  this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                  this.posY + 0.5 + (double)(this.random.nextFloat() * this.height),
                  this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                  offset,
                  offset2,
                  offset3,
                  new int[0]
               );
         }
      }
   }

   @Override
   public boolean nextInt() {
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.getBoundingBox2().minY);
      int count3 = MathHelper.ceiling_double_int(this.posZ);
      return this.worldObj.getIBlockState(count, count2 - 1, count3).getBlock() == this.block && this.worldObj.computeCount(count, count2, count3) > 8 && super.nextInt();
   }

   public HitBoxBuffer(World world) {
      super(world);
   }
}
