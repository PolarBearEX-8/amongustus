package net.minecraft.tileentity;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityFlowerPot extends TileEntity {
   private Item flowerPotItem;
   private int flowerPotData;

   public int getFlowerPotData() {
      return this.flowerPotData;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      ResourceLocation nameForObject = (ResourceLocation)Item.itemRegistry.getNameForObject(this.flowerPotItem);
      tag.setString("Item", nameForObject == null ? "" : nameForObject.toString());
      tag.setInteger("Data", this.flowerPotData);
   }

   public void setFlowerPotData(Item item, int n) {
      this.flowerPotItem = item;
      this.flowerPotData = n;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      if (tag.hasKey("Item", 8)) {
         this.flowerPotItem = Item.getByNameOrId(tag.getString("Item"));
      } else {
         this.flowerPotItem = Item.getItemById(tag.getInteger("Item"));
      }

      this.flowerPotData = tag.getInteger("Data");
   }

   public Item getFlowerPotItem() {
      return this.flowerPotItem;
   }

   public TileEntityFlowerPot() {
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound tag = new NBTTagCompound();
      this.writeToNBT(tag);
      tag.removeTag("Item");
      tag.setInteger("Item", Item.getIdFromItem(this.flowerPotItem));
      return new S35PacketUpdateTileEntity(this.pos, 5, tag);
   }

   public TileEntityFlowerPot(Item item, int n) {
      this.flowerPotItem = item;
      this.flowerPotData = n;
   }
}
