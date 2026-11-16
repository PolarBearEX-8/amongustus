package net.minecraft.block;

import client.setting.MovementNumberProperty;
import client.util.IBlockState;
import client.util.IInventoryExtensionAdapter;
import client.util.InternalIInventoryExtension;
import client.util.InterpolationPipeline;
import client.util.LivingEntityFilter;
import java.util.List;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockPosEntry extends IInventoryExtensionAdapter implements InternalIInventoryExtension {
   public boolean enabled19 = true;
   public int count14 = -1;
   public BlockPos blockPos2 = BlockPos.origin;

   @Override
   public double getValue27() {
      return this.posZ;
   }

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (!this.worldObj.state) {
         player.applyIInventory(this);
      }

      return true;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         this.resolveEntityItem(Item.processAction2(Blocks.blockHopper), 1, 0.0F);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count14 = tagCompound.computeCount("TransferCooldown");
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.HOPPER;
   }

   public void setCount14(int count) {
      this.count14 = count;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new InterpolationPipeline(inventoryPlayer, this, player);
   }

   @Override
   public double getValue28() {
      return this.posX;
   }

   @Override
   public IBlockState getIBlockState() {
      return Blocks.blockHopper.getDefaultState();
   }

   @Override
   public void applyIndex(int count, int count2, int count3, boolean flag) {
      boolean flag2 = !flag;
      if (flag2 != this.isEnabled19()) {
         this.setEnabled19(flag2);
      }
   }

   @Override
   public int processAction4() {
      return 5;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("TransferCooldown", this.count14);
   }

   @Override
   public double getValue29() {
      return this.posY + 0.5;
   }

   @Override
   public World getWorld3() {
      return this.worldObj;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:hopper";
   }

   public boolean isActive18() {
      return this.count14 > 0;
   }

   public boolean isEnabled19() {
      return this.enabled19;
   }

   public boolean isActive19() {
      if (TileEntityHopper.matchesInternalIInventoryExtensio(this)) {
         return true;
      } else {
         List list = this.worldObj.resolveList(EntityItem.class, this.getBoundingBox2().offset(0.25, 0.0, 0.25), LivingEntityFilter.predicate);
         if (list.size() > 0) {
            TileEntityHopper.matchesIInventory(this, (EntityItem)list.get(0));
         }

         return false;
      }
   }

   @Override
   public int getCount4() {
      return 1;
   }

   @Override
   public void updateState() {
      super.updateState();
      if (!this.worldObj.state && this.canExecute() && this.isEnabled19()) {
         if (new BlockPos(this).equals(this.blockPos2)) {
            this.count14--;
         } else {
            this.setCount14(0);
         }

         if (!this.isActive18()) {
            this.setCount14(0);
            if (this.isActive19()) {
               this.setCount14(4);
               this.processAction5();
            }
         }
      }
   }

   public void setEnabled19(boolean enabled) {
      this.enabled19 = enabled;
   }

   public BlockPosEntry(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }

   public BlockPosEntry(World world) {
      super(world);
   }
}
