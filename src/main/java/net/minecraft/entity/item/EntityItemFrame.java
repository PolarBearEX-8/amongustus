package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityItemFrame extends EntityHanging {
   private float itemDropChance = 1.0F;

   @Override
   public void onBroken(Entity entity) {
      this.dropItemOrSelf(entity, true);
   }

   @Override
   public int getHeightPixels() {
      return 12;
   }

   public ItemStack getDisplayedItem() {
      return this.getDataWatcher().getWatchableObjectItemStack(8);
   }

   public EntityItemFrame(World world) {
      super(world);
   }

   public void setDisplayedItem(ItemStack stack) {
      this.setDisplayedItemWithUpdate(stack, true);
   }

   @Override
   public float getCollisionBorderSize() {
      return 0.0F;
   }

   @Override
   public void entityInit() {
      this.getDataWatcher().addObjectByDataType(8, 5);
      this.getDataWatcher().addObject(9, (byte)0);
   }

   @Override
   public boolean isInRangeToRenderDist(double d) {
      double d2 = 16.0;
      d2 = d2 * 64.0 * this.renderDistanceWeight;
      return d < d2 * d2;
   }

   public void setItemRotation(int n) {
      this.setItemRotation(n, true);
   }

   private void setItemRotation(int n, boolean flag) {
      this.getDataWatcher().updateObject(9, (byte)(n % 8));
      if (flag && this.hangingPosition != null) {
         this.entityWorld.updateComparatorOutputLevel(this.hangingPosition, Blocks.air);
      }
   }

   private void setDisplayedItemWithUpdate(ItemStack stack, boolean flag) {
      if (stack != null) {
         stack = stack.copy();
         stack.stackSize = 1;
         stack.setItemFrame(this);
      }

      this.getDataWatcher().updateObject(8, stack);
      this.getDataWatcher().setObjectWatched(8);
      if (flag && this.hangingPosition != null) {
         this.entityWorld.updateComparatorOutputLevel(this.hangingPosition, Blocks.air);
      }
   }

   private void removeFrameFromMap(ItemStack stack) {
      if (stack != null) {
         if (stack.getItem() == Items.filled_map) {
            ((ItemMap)stack.getItem()).getMapData(stack, this.entityWorld).mapDecorations.remove("frame-" + this.getEntityId());
         }

         stack.setItemFrame((EntityItemFrame)null);
      }
   }

   @Override
   public boolean interactFirst(EntityPlayer player) {
      if (this.getDisplayedItem() == null) {
         ItemStack heldItem = player.getHeldItem();
         if (heldItem != null && !this.entityWorld.isRemote) {
            this.setDisplayedItem(heldItem);
            if (!player.capabilities.isCreativeMode && --heldItem.stackSize <= 0) {
               player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
         }
      } else if (!this.entityWorld.isRemote) {
         this.setItemRotation(this.getRotation() + 1);
      }

      return true;
   }

   public EntityItemFrame(World world, BlockPos pos, EnumFacing facing) {
      super(world, pos);
      this.updateFacingWithBoundingBox(facing);
   }

   @Override
   public int getWidthPixels() {
      return 12;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      NBTTagCompound compoundTag = tag.getCompoundTag("Item");
      if (compoundTag != null && !compoundTag.hasNoTags()) {
         this.setDisplayedItemWithUpdate(ItemStack.loadItemStackFromNBT(compoundTag), false);
         this.setItemRotation(tag.getByte("ItemRotation"), false);
         if (tag.hasKey("ItemDropChance", 99)) {
            this.itemDropChance = tag.getFloat("ItemDropChance");
         }

         if (tag.hasKey("Direction")) {
            this.setItemRotation(this.getRotation() * 2, false);
         }
      }

      super.readEntityFromNBT(tag);
   }

   public int getAnalogOutput() {
      return this.getDisplayedItem() == null ? 0 : this.getRotation() % 8 + 1;
   }

   public int getRotation() {
      return this.getDataWatcher().getWatchableObjectByte(9);
   }

   public void dropItemOrSelf(Entity entity, boolean flag) {
      if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
         ItemStack displayedItem = this.getDisplayedItem();
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).capabilities.isCreativeMode) {
               this.removeFrameFromMap(displayedItem);
               return;
            }
         }

         if (flag) {
            this.entityDropItem(new ItemStack(Items.item_frame), 0.0F);
         }

         if (displayedItem != null && this.rand.nextFloat() < this.itemDropChance) {
            displayedItem = displayedItem.copy();
            this.removeFrameFromMap(displayedItem);
            this.entityDropItem(displayedItem, 0.0F);
         }
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (!damageSource.isExplosion() && this.getDisplayedItem() != null) {
         if (!this.entityWorld.isRemote) {
            this.dropItemOrSelf(damageSource.getEntity(), false);
            this.setDisplayedItem((ItemStack)null);
         }

         return true;
      } else {
         return super.attackEntityFrom(damageSource, f);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      if (this.getDisplayedItem() != null) {
         tag.setTag("Item", this.getDisplayedItem().writeToNBT(new NBTTagCompound()));
         tag.setByte("ItemRotation", (byte)this.getRotation());
         tag.setFloat("ItemDropChance", this.itemDropChance);
      }

      super.writeEntityToNBT(tag);
   }
}
