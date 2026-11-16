package augustus.modes.movement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderchest;

public class MovementContainerEnderchestMode extends InventoryLargeChest {
   public TileEntityEnderchest tileEntityEnderchest;

   public NBTTagList getNBTTagList() {
      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.processAction4(); index++) {
         ItemStack stack = this.processAction3(index);
         if (stack != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.put("Slot", (byte)index);
            stack.resolveNBTTagCompound(tagCompound);
            nBTTagList.applyLISTManager(tagCompound);
         }
      }

      return nBTTagList;
   }

   @Override
   public void processAction(EntityPlayer player) {
      if (this.tileEntityEnderchest != null) {
         this.tileEntityEnderchest.updateState6();
      }

      super.processAction(player);
      this.tileEntityEnderchest = null;
   }

   public void applyNBTTagList(NBTTagList nBTTagList) {
      for (int index = 0; index < this.processAction4(); index++) {
         this.processAction(index, (ItemStack)null);
      }

      for (int index2 = 0; index2 < nBTTagList.getInt(); index2++) {
         NBTTagCompound nBTTagCompound = nBTTagList.getNBTTagCompound(index2);
         int count = nBTTagCompound.computeByteCode("Slot") & 255;
         if (count >= 0 && count < this.processAction4()) {
            this.processAction(count, ItemStack.resolveItemStack(nBTTagCompound));
         }
      }
   }

   public void setTileEntityEnderchest(TileEntityEnderchest tileEntityEnderchest2) {
      this.tileEntityEnderchest = tileEntityEnderchest2;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.tileEntityEnderchest != null && !this.tileEntityEnderchest.matchesEntityPlayerByEntityPlayer(player) ? false : super.checkEntityPlayer(player);
   }

   @Override
   public void processAction2(EntityPlayer player) {
      if (this.tileEntityEnderchest != null) {
         this.tileEntityEnderchest.updateState2();
      }

      super.processAction2(player);
   }

   public MovementContainerEnderchestMode() {
      super("container.enderchest", false, 27);
   }
}
