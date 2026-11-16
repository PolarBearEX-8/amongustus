package net.minecraft.inventory;

import client.event.PostUpdateDispatcher;
import client.util.BaseManagerStyleIChatComponentAdapter;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class InventoryLargeChest implements IInventory {
   public String labelText;
   public IChatComponent iChatComponent;
   public int count;
   public ItemStack[] itemStackArray;
   public List list;
   public boolean enabled;

   @Override
   public ItemStack getItemStack(int count2, int count3) {
      if (this.itemStackArray[count2] != null) {
         if (this.itemStackArray[count2].animationsToGo <= count3) {
            ItemStack stack = this.itemStackArray[count2];
            this.itemStackArray[count2] = null;
            this.processAction5();
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count2].resolveItemStack(count3);
            if (this.itemStackArray[count2].animationsToGo == 0) {
               this.itemStackArray[count2] = null;
            }

            this.processAction5();
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public boolean processAction() {
      return this.enabled;
   }

   @Override
   public int processAction(int count2) {
      return 0;
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
   public void processAction(int count2, ItemStack stack) {
      this.itemStackArray[count2] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }

      this.processAction5();
   }

   @Override
   public String getText() {
      return this.labelText;
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

   public void applyPostUpdateDispatcher(PostUpdateDispatcher postUpdateDispatcher) {
      if (this.list == null) {
         this.list = Lists.newArrayList();
      }

      this.list.add(postUpdateDispatcher);
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public int processAction3() {
      return 0;
   }

   @Override
   public boolean processAction2(int count2, ItemStack stack) {
      return true;
   }

   public void applyText(String text) {
      this.enabled = true;
      this.labelText = text;
   }

   public void remove(PostUpdateDispatcher postUpdateDispatcher) {
      this.list.remove(postUpdateDispatcher);
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public ItemStack resolveItemStack(ItemStack stack) {
      ItemStack stack2 = stack.getItemStack();

      for (int index = 0; index < this.count; index++) {
         ItemStack stack3 = this.processAction3(index);
         if (stack3 == null) {
            this.processAction(index, stack2);
            this.processAction5();
            return null;
         }

         if (ItemStack.matchesItemStackByItemStack(stack3, stack2)) {
            int count2 = Math.min(this.processAction2(), stack3.processAction5());
            int count3 = Math.min(stack2.animationsToGo, count2 - stack3.animationsToGo);
            if (count3 > 0) {
               stack3.animationsToGo += count3;
               stack2.animationsToGo -= count3;
               if (stack2.animationsToGo <= 0) {
                  this.processAction5();
                  return null;
               }
            }
         }
      }

      if (stack2.animationsToGo != stack.animationsToGo) {
         this.processAction5();
      }

      return stack2;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public void processAction(int count2, int count3) {
   }

   @Override
   public int processAction4() {
      return this.count;
   }

   @Override
   public void processAction5() {
      if (this.list != null) {
         for (int index = 0; index < this.list.size(); index++) {
            ((PostUpdateDispatcher)this.list.get(index)).applyInventoryLargeChest(this);
         }
      }
   }

   @Override
   public ItemStack processAction3(int count2) {
      return count2 >= 0 && count2 < this.itemStackArray.length ? this.itemStackArray[count2] : null;
   }

   public InventoryLargeChest(IChatComponent iChatComponent2, int count2) {
      this(iChatComponent2.getString(), true, count2);
      this.iChatComponent = iChatComponent2;
   }

   public InventoryLargeChest(String text, boolean flag, int count2) {
      this.labelText = text;
      this.enabled = flag;
      this.count = count2;
      this.itemStackArray = new ItemStack[count2];
   }
}
