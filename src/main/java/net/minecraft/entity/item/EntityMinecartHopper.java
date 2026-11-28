package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {
   private boolean isBlocked = true;
   private int transferTicker = -1;
   private BlockPos lastPosition = BlockPos.ORIGIN;

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerHopper(inventoryPlayer, this, player);
   }

   public void setBlocked(boolean flag) {
      this.isBlocked = flag;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.entityWorld.isRemote && this.isEntityAlive() && this.getBlocked()) {
         if (new BlockPos(this).equals(this.lastPosition)) {
            this.transferTicker--;
         } else {
            this.setTransferTicker(0);
         }

         if (!this.canTransfer()) {
            this.setTransferTicker(0);
            if (this.captureDroppedItems()) {
               this.setTransferTicker(4);
               this.markDirty();
            }
         }
      }
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.hopper.getDefaultState();
   }

   @Override
   public int getDefaultDisplayTileOffset() {
      return 1;
   }

   @Override
   public boolean interactFirst(EntityPlayer player) {
      if (!this.entityWorld.isRemote) {
         player.displayGUIChest(this);
      }

      return true;
   }

   public void setTransferTicker(int n) {
      this.transferTicker = n;
   }

   @Override
   public void killMinecart(DamageSource damageSource) {
      super.killMinecart(damageSource);
      if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
         this.dropItemWithOffset(Item.getItemFromBlock(Blocks.hopper), 1, 0.0F);
      }
   }

   public EntityMinecartHopper(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   @Override
   public World getWorld() {
      return this.entityWorld;
   }

   @Override
   public int getSizeInventory() {
      return 5;
   }

   @Override
   public String getGuiID() {
      return "minecraft:hopper";
   }

   public EntityMinecartHopper(World world) {
      super(world);
   }

   public boolean getBlocked() {
      return this.isBlocked;
   }

   @Override
   public double getZPos() {
      return this.posZ;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("TransferCooldown", this.transferTicker);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.transferTicker = tag.getInteger("TransferCooldown");
   }

   @Override
   public void onActivatorRailPass(int n, int n2, int n3, boolean flag) {
      boolean flag2 = !flag;
      if (flag2 != this.getBlocked()) {
         this.setBlocked(flag2);
      }
   }

   @Override
   public double getYPos() {
      return this.posY + 0.5;
   }

   public boolean canTransfer() {
      return this.transferTicker > 0;
   }

   @Override
   public double getXPos() {
      return this.posX;
   }

   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.HOPPER;
   }

   public boolean captureDroppedItems() {
      if (TileEntityHopper.captureDroppedItems(this)) {
         return true;
      } else {
         List entitiesWithinAABB = this.entityWorld.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().expand(0.25, 0.0, 0.25), EntitySelectors.selectAnything);
         if (entitiesWithinAABB.size() > 0) {
            TileEntityHopper.putDropInInventoryAllSlots(this, (EntityItem)entitiesWithinAABB.get(0));
         }

         return false;
      }
   }
}
