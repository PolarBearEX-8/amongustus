package client.util;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AuxiliaryArrayListAdapter extends ArrayList {
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Recipes", 10);

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         this.add(new DistanceBuffer(nBTTagCompound));
      }
   }

   public AuxiliaryArrayListAdapter() {
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.size(); index++) {
         DistanceBuffer distanceBuffer = (DistanceBuffer)this.get(index);
         nBTTagList.applyLISTManager(distanceBuffer.getNBTTagCompound());
      }

      tagCompound.applyText("Recipes", nBTTagList);
      return tagCompound;
   }

   public AuxiliaryArrayListAdapter(NBTTagCompound tagCompound) {
      this.applyNBTTagCompound(tagCompound);
   }

   public void applyPacketBuffer(PacketBuffer packet) {
      packet.writeByte((byte)(this.size() & 0xFF));

      for (int index = 0; index < this.size(); index++) {
         DistanceBuffer distanceBuffer = (DistanceBuffer)this.get(index);
         packet.applyItemStack(distanceBuffer.getItemStack());
         packet.applyItemStack(distanceBuffer.getItemStack3());
         ItemStack stack = distanceBuffer.getItemStack2();
         packet.writeBoolean(stack != null);
         if (stack != null) {
            packet.applyItemStack(stack);
         }

         packet.writeBoolean(distanceBuffer.isActive());
         packet.writeInt(distanceBuffer.getCount());
         packet.writeInt(distanceBuffer.getCount2());
      }
   }

   private boolean matchesItemStack(ItemStack stack, ItemStack stack2) {
      return ItemStack.matchesItemStackByItemStack(stack, stack2)
         && (!stack2.canExecute() || stack.canExecute() && AuxiliaryNameManager.matchesLISTManager(stack2.getStackTagCompound(), stack.getStackTagCompound(), false));
   }

   public DistanceBuffer resolveDistanceBuffer(ItemStack stack, ItemStack stack2, int count) {
      if (count > 0 && count < this.size()) {
         DistanceBuffer distanceBuffer = (DistanceBuffer)this.get(count);
         return this.matchesItemStack(stack, distanceBuffer.getItemStack())
               && (stack2 == null && !distanceBuffer.isActiveAsFlag() || distanceBuffer.isActiveAsFlag() && this.matchesItemStack(stack2, distanceBuffer.getItemStack2()))
               && stack.animationsToGo >= distanceBuffer.getItemStack().animationsToGo
               && (!distanceBuffer.isActiveAsFlag() || stack2.animationsToGo >= distanceBuffer.getItemStack2().animationsToGo)
            ? distanceBuffer
            : null;
      } else {
         for (int index = 0; index < this.size(); index++) {
            DistanceBuffer distanceBuffer2 = (DistanceBuffer)this.get(index);
            if (this.matchesItemStack(stack, distanceBuffer2.getItemStack())
               && stack.animationsToGo >= distanceBuffer2.getItemStack().animationsToGo
               && (
                  !distanceBuffer2.isActiveAsFlag() && stack2 == null
                     || distanceBuffer2.isActiveAsFlag()
                        && this.matchesItemStack(stack2, distanceBuffer2.getItemStack2())
                        && stack2.animationsToGo >= distanceBuffer2.getItemStack2().animationsToGo
               )) {
               return distanceBuffer2;
            }
         }

         return null;
      }
   }

   public static AuxiliaryArrayListAdapter resolveAuxiliaryArrayListAdapter(PacketBuffer packet) throws IOException {
      AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = new AuxiliaryArrayListAdapter();
      int count = packet.readByte() & 255;

      for (int index = 0; index < count; index++) {
         ItemStack stack = packet.getItemStack();
         ItemStack stack2 = packet.getItemStack();
         ItemStack stack3 = null;
         if (packet.readBoolean()) {
            stack3 = packet.getItemStack();
         }

         boolean flag = packet.readBoolean();
         int count2 = packet.readInt();
         int count3 = packet.readInt();
         DistanceBuffer distanceBuffer = new DistanceBuffer(stack, stack3, stack2, count2, count3);
         if (flag) {
            distanceBuffer.updateState();
         }

         auxiliaryArrayListAdapter.add(distanceBuffer);
      }

      return auxiliaryArrayListAdapter;
   }
}
