package net.minecraft.tileentity;

import client.util.Packet;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityFlowerpot extends TileEntity {
   public Item item;
   public int count2;

   public void applyItem(Item item2, int count) {
      this.item = item2;
      this.count2 = count;
   }

   public Item getItem() {
      return this.item;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("Item", 8)) {
         this.item = Item.getItem(tagCompound.resolveText("Item"));
      } else {
         this.item = Item.processAction(tagCompound.computeCount("Item"));
      }

      this.count2 = tagCompound.computeCount("Data");
   }

   @Override
   public Packet getPacket() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound);
      tagCompound.remove("Item");
      tagCompound.put("Item", Item.getIndex(this.item));
      return new S35PacketUpdateTileEntity(this.blockPos, 5, tagCompound);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      ResourceLocation object = (ResourceLocation)Item.iterableExtensionAdapter.getObject2(this.item);
      tagCompound.put("Item", object == null ? "" : object.toString());
      tagCompound.put("Data", this.count2);
   }

   public TileEntityFlowerpot(Item item2, int count) {
      this.item = item2;
      this.count2 = count;
   }

   public TileEntityFlowerpot() {
   }
}
