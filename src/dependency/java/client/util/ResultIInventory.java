package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ResultIInventory implements IInventory {
   public ItemStack[] itemStackArray = new ItemStack[1];

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public boolean processAction() {
      return false;
   }

   @Override
   public ItemStack getItemStack(int count, int count2) {
      if (this.itemStackArray[0] != null) {
         ItemStack stack = this.itemStackArray[0];
         this.itemStackArray[0] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public int processAction(int count) {
      return 0;
   }

   @Override
   public ItemStack getStackInSlot(int count) {
      if (this.itemStackArray[0] != null) {
         ItemStack stack = this.itemStackArray[0];
         this.itemStackArray[0] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      this.itemStackArray[0] = stack;
   }

   @Override
   public String getText() {
      return "Result";
   }

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.processAction()
         ? new ChatComponentText(this.getText())
         : new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]));
   }

   @Override
   public int processAction2() {
      return 64;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return true;
   }

   @Override
   public int processAction3() {
      return 0;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public int processAction4() {
      return 1;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public void processAction(int count, int count2) {
   }

   @Override
   public void processAction5() {
   }

   @Override
   public ItemStack processAction3(int count) {
      return this.itemStackArray[0];
   }
}
