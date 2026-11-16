package net.minecraft.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFilledMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityItemframe extends CustomEntityAdapter {
   public float factor7 = 1.0F;

   @Override
   public boolean checkDouble(double offset) {
      double offset2 = 16.0 * 64.0 * this.value3;
      return offset < offset2 * offset2;
   }

   @Override
   public float getHeight() {
      return 0.0F;
   }

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (this.getItemStack() == null) {
         ItemStack stack = player.getItemStack();
         if (stack != null && !this.worldObj.state) {
            this.applyItemStackByItemStack(stack);
            if (!player.invulnerableManager.enabled2 && --stack.animationsToGo <= 0) {
               player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
            }
         }
      } else if (!this.worldObj.state) {
         this.applyIndex5(this.getCount6() + 1);
      }

      return true;
   }

   @Override
   public void applyEntity9(Entity entity) {
      this.applyEntity(entity, true);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Item");
      if (nBTTagCompound != null && !nBTTagCompound.isEmpty()) {
         this.applyItemStack(ItemStack.resolveItemStack(nBTTagCompound), false);
         this.applyIndexByIndex(tagCompound.computeByteCode("ItemRotation"), false);
         if (tagCompound.matchesText("ItemDropChance", 99)) {
            this.factor7 = tagCompound.computeFactor("ItemDropChance");
         }

         if (tagCompound.containsKey("Direction")) {
            this.applyIndexByIndex(this.getCount6() * 2, false);
         }
      }

      super.applyNBTTagCompound(tagCompound);
   }

   private void applyIndexByIndex(int count, boolean flag) {
      this.getDataWatcher().applyTargetByTarget(9, (byte)(count % 8));
      if (flag && this.blockPos2 != null) {
         this.worldObj.applyBlockPos(this.blockPos2, Blocks.air);
      }
   }

   private void applyItemStack(ItemStack stack, boolean flag) {
      ItemStack stack2 = stack;
      if (stack != null) {
         stack2 = stack.getItemStack();
         stack2.animationsToGo = 1;
         stack2.setEntityItemframe(this);
      }

      this.getDataWatcher().applyTargetByTarget(8, stack2);
      this.getDataWatcher().applyIndex(8);
      if (flag && this.blockPos2 != null) {
         this.worldObj.applyBlockPos(this.blockPos2, Blocks.air);
      }
   }

   private void applyItemStack(ItemStack stack) {
      if (stack != null) {
         if (stack.getItem() == Items.filledMap) {
            ((ItemFilledMap)stack.getItem()).resolveDimensionStringEntryByItemStack(stack, this.worldObj).map.remove("frame-" + this.getEntityId());
         }

         stack.setEntityItemframe((EntityItemframe)null);
      }
   }

   public void applyEntity(Entity entity, boolean flag) {
      if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         ItemStack stack = this.getItemStack();
         if (entity instanceof EntityPlayer && ((EntityPlayer)entity).invulnerableManager.enabled2) {
            this.applyItemStack(stack);
            return;
         }

         if (flag) {
            this.resolveEntityItem(new ItemStack(Items.itemFrame), 0.0F);
         }

         if (stack != null && this.random.nextFloat() < this.factor7) {
            ItemStack stack2 = stack.getItemStack();
            this.applyItemStack(stack2);
            this.resolveEntityItem(stack2, 0.0F);
         }
      }
   }

   public int getCount6() {
      return this.getDataWatcher().computeByteCode(9);
   }

   public ItemStack getItemStack() {
      return this.getDataWatcher().getItemStack(8);
   }

   public void applyIndex5(int count) {
      this.applyIndexByIndex(count, true);
   }

   public int getCount7() {
      return this.getItemStack() == null ? 0 : this.getCount6() % 8 + 1;
   }

   public void applyItemStackByItemStack(ItemStack stack) {
      this.applyItemStack(stack, true);
   }

   @Override
   public int getCount5() {
      return 12;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (!damageSource.isEnabled5() && this.getItemStack() != null) {
         if (!this.worldObj.state) {
            this.applyEntity(damageSource.getEntity(), false);
            this.applyItemStackByItemStack((ItemStack)null);
         }

         return true;
      } else {
         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      if (this.getItemStack() != null) {
         tagCompound.applyText("Item", this.getItemStack().resolveNBTTagCompound(new NBTTagCompound()));
         tagCompound.put("ItemRotation", (byte)this.getCount6());
         tagCompound.put("ItemDropChance", this.factor7);
      }

      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   @Override
   public int getCount4() {
      return 12;
   }

   @Override
   public void updateState7() {
      this.getDataWatcher().applyIndex(8, 5);
      this.getDataWatcher().applyTarget(9, (byte)0);
   }

   public EntityItemframe(World world) {
      super(world);
   }

   public EntityItemframe(World world, BlockPos blockPos, EnumFacing enumFacing) {
      super(world, blockPos);
      this.applyEnumFacing(enumFacing);
   }
}
