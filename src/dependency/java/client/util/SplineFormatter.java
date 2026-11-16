package client.util;

import client.event.LocalListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SplineFormatter implements IInventory {
   public final LocalListener localListener;
   public ItemStack[] itemStackArray = new ItemStack[3];
   public final EntityPlayer entityPlayer;
   public DistanceBuffer distanceBuffer;
   public int count;

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public String getText() {
      return "mob.villager";
   }

   private boolean checkIndex(int count2) {
      return count2 == 0 || count2 == 1;
   }

   @Override
   public boolean processAction2(int count2, ItemStack stack) {
      return true;
   }

   @Override
   public int processAction(int count2) {
      return 0;
   }

   @Override
   public ItemStack processAction3(int count2) {
      return this.itemStackArray[count2];
   }

   @Override
   public ItemStack getStackInSlot(int count2) {
      if (this.itemStackArray[count2] != null) {
         ItemStack stack = this.itemStackArray[count2];
         this.itemStackArray[count2] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public int processAction2() {
      return 64;
   }

   public DistanceBuffer getDistanceBuffer() {
      return this.distanceBuffer;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.localListener.getEntityPlayer2() == player;
   }

   @Override
   public int processAction3() {
      return 0;
   }

   @Override
   public void processAction(int count2, ItemStack stack) {
      this.itemStackArray[count2] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }

      if (this.checkIndex(count2)) {
         this.updateState();
      }
   }

   @Override
   public int processAction4() {
      return this.itemStackArray.length;
   }

   public void updateState() {
      this.distanceBuffer = null;
      ItemStack stack = this.itemStackArray[0];
      ItemStack stack2 = this.itemStackArray[1];
      if (stack == null) {
         stack = stack2;
         stack2 = null;
      }

      if (stack == null) {
         this.processAction(2, (ItemStack)null);
      } else {
         AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = this.localListener.resolveAuxiliaryArrayListAdapterByEntityPlayer(this.entityPlayer);
         if (auxiliaryArrayListAdapter != null) {
            DistanceBuffer distanceBuffer2 = auxiliaryArrayListAdapter.resolveDistanceBuffer(stack, stack2, this.count);
            if (distanceBuffer2 != null && !distanceBuffer2.isActive()) {
               this.distanceBuffer = distanceBuffer2;
               this.processAction(2, distanceBuffer2.getItemStack3().getItemStack());
            } else if (stack2 != null) {
               DistanceBuffer distanceBuffer3 = auxiliaryArrayListAdapter.resolveDistanceBuffer(stack2, stack, this.count);
               if (distanceBuffer3 != null && !distanceBuffer3.isActive()) {
                  this.distanceBuffer = distanceBuffer3;
                  this.processAction(2, distanceBuffer3.getItemStack3().getItemStack());
               } else {
                  this.processAction(2, (ItemStack)null);
               }
            } else {
               this.processAction(2, (ItemStack)null);
            }
         }
      }

      this.localListener.applyItemStack(this.processAction3(2));
   }

   @Override
   public void processAction5() {
      this.updateState();
   }

   public void applyIndex(int count2) {
      this.count = count2;
      this.updateState();
   }

   public SplineFormatter(EntityPlayer player, LocalListener localListener2) {
      this.entityPlayer = player;
      this.localListener = localListener2;
   }

   @Override
   public boolean processAction() {
      return false;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public ItemStack getItemStack(int count2, int count3) {
      if (this.itemStackArray[count2] != null) {
         if (count2 == 2) {
            ItemStack stack = this.itemStackArray[count2];
            this.itemStackArray[count2] = null;
            return stack;
         } else if (this.itemStackArray[count2].animationsToGo <= count3) {
            ItemStack stack2 = this.itemStackArray[count2];
            this.itemStackArray[count2] = null;
            if (this.checkIndex(count2)) {
               this.updateState();
            }

            return stack2;
         } else {
            ItemStack stack3 = this.itemStackArray[count2].resolveItemStack(count3);
            if (this.itemStackArray[count2].animationsToGo == 0) {
               this.itemStackArray[count2] = null;
            }

            if (this.checkIndex(count2)) {
               this.updateState();
            }

            return stack3;
         }
      } else {
         return null;
      }
   }

   @Override
   public void processAction(int count2, int count3) {
   }

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.processAction()
         ? new ChatComponentText(this.getText())
         : new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]));
   }
}
