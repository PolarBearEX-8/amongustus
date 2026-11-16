package net.minecraft.tileentity;

import client.setting.LocalOptionType;
import client.util.AngleAccumulator;
import client.util.Packet;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityBanner extends TileEntity {
   public int count2;
   public NBTTagList nBTTagList;
   public boolean enabled2;
   public List list;
   public List list2;
   public String labelText;

   public List getListAsList() {
      this.updateState();
      return this.list2;
   }

   public void applyItemStack(ItemStack stack) {
      this.nBTTagList = null;
      if (stack.canExecute() && stack.getStackTagCompound().matchesText("BlockEntityTag", 10)) {
         NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("BlockEntityTag");
         if (nBTTagCompound.containsKey("Patterns")) {
            this.nBTTagList = (NBTTagList)nBTTagCompound.getExtendedLoggerEntry("Patterns", 10).getLISTManager();
         }

         if (nBTTagCompound.matchesText("Base", 99)) {
            this.count2 = nBTTagCompound.computeCount("Base");
         } else {
            this.count2 = stack.getIndexOffset() & 15;
         }
      } else {
         this.count2 = stack.getIndexOffset() & 15;
      }

      this.list = null;
      this.list2 = null;
      this.labelText = "";
      this.enabled2 = true;
   }

   public int getCount2() {
      return this.count2;
   }

   public static int computeCount(ItemStack stack) {
      NBTTagCompound tagCompound = stack.resolveNBTTagCompound("BlockEntityTag", false);
      return tagCompound != null && tagCompound.containsKey("Patterns") ? tagCompound.getExtendedLoggerEntry("Patterns", 10).getInt() : 0;
   }

   private void updateState() {
      if (this.list == null || this.list2 == null || this.labelText == null) {
         if (!this.enabled2) {
            this.labelText = "";
         } else {
            this.list = Lists.newArrayList();
            this.list2 = Lists.newArrayList();
            this.list.add(AngleAccumulator.BASE);
            this.list2.add(LocalOptionType.resolveLocalOptionTypeByIndex(this.count2));
            this.labelText = "b" + this.count2;
            if (this.nBTTagList != null) {
               for (int index = 0; index < this.nBTTagList.getInt(); index++) {
                  NBTTagCompound nBTTagCompound = this.nBTTagList.getNBTTagCompound(index);
                  AngleAccumulator angleAccumulator = AngleAccumulator.resolveAngleAccumulator(nBTTagCompound.resolveText("Pattern"));
                  if (angleAccumulator != null) {
                     this.list.add(angleAccumulator);
                     int count = nBTTagCompound.computeCount("Color");
                     this.list2.add(LocalOptionType.resolveLocalOptionTypeByIndex(count));
                     this.labelText = this.labelText + angleAccumulator.getLabelText2() + count;
                  }
               }
            }
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.count2 = tagCompound.computeCount("Base");
      this.nBTTagList = tagCompound.getExtendedLoggerEntry("Patterns", 10);
      this.list = null;
      this.list2 = null;
      this.labelText = null;
      this.enabled2 = true;
   }

   public List getList() {
      this.updateState();
      return this.list;
   }

   public static int computeCountByItemStack(ItemStack stack) {
      NBTTagCompound tagCompound = stack.resolveNBTTagCompound("BlockEntityTag", false);
      return tagCompound != null && tagCompound.containsKey("Base") ? tagCompound.computeCount("Base") : stack.getIndexOffset();
   }

   public String getText() {
      this.updateState();
      return this.labelText;
   }

   public NBTTagList getNBTTagList() {
      return this.nBTTagList;
   }

   @Override
   public Packet getPacket() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound);
      return new S35PacketUpdateTileEntity(this.blockPos, 6, tagCompound);
   }

   public static void applyNBTTagCompound(NBTTagCompound tagCompound, int count, NBTTagList nBTTagList2) {
      tagCompound.put("Base", count);
      if (nBTTagList2 != null) {
         tagCompound.applyText("Patterns", nBTTagList2);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      applyNBTTagCompound(tagCompound, this.count2, this.nBTTagList);
   }

   public static void applyItemStackByItemStack(ItemStack stack) {
      NBTTagCompound tagCompound = stack.resolveNBTTagCompound("BlockEntityTag", false);
      if (tagCompound != null && tagCompound.matchesText("Patterns", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Patterns", 10);
         if (extendedLoggerEntry.getInt() > 0) {
            extendedLoggerEntry.remove(extendedLoggerEntry.getInt() - 1);
            if (extendedLoggerEntry.isEmpty()) {
               stack.getStackTagCompound().remove("BlockEntityTag");
               if (stack.getStackTagCompound().isEmpty()) {
                  stack.processAction((NBTTagCompound)null);
               }
            }
         }
      }
   }
}
