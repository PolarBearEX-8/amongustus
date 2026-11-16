package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ContainerCraftingIInventory implements IInventory {
   public final ItemStack[] itemStackArray;
   public final int count;
   public final int count2;
   public final Container container;

   @Override
   public int processAction(int count3) {
      return 0;
   }

   @Override
   public ItemStack getItemStack(int count3, int count4) {
      if (this.itemStackArray[count3] != null) {
         if (this.itemStackArray[count3].animationsToGo <= count4) {
            ItemStack stack = this.itemStackArray[count3];
            this.itemStackArray[count3] = null;
            this.container.processAction(this);
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count3].resolveItemStack(count4);
            if (this.itemStackArray[count3].animationsToGo == 0) {
               this.itemStackArray[count3] = null;
            }

            this.container.processAction(this);
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public boolean processAction() {
      return false;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public void processAction(int count3, ItemStack stack) {
      this.itemStackArray[count3] = stack;
      this.container.processAction(this);
   }

   @Override
   public ItemStack getStackInSlot(int count3) {
      if (this.itemStackArray[count3] != null) {
         ItemStack stack = this.itemStackArray[count3];
         this.itemStackArray[count3] = null;
         return stack;
      } else {
         return null;
      }
   }

   public ItemStack resolveItemStack(int count3, int count4) {
      return count3 >= 0 && count3 < this.count && count4 >= 0 && count4 <= this.count2 ? this.processAction3(count3 + count4 * this.count) : null;
   }

   @Override
   public String getText() {
      return "container.crafting";
   }

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.processAction()
         ? new ChatComponentText(this.getText())
         : new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]));
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public int processAction2() {
      return 64;
   }

   @Override
   public int processAction3() {
      return 0;
   }

   @Override
   public boolean processAction2(int count3, ItemStack stack) {
      return true;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public int processAction4() {
      return this.itemStackArray.length;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public void processAction(int count3, int count4) {
   }

   @Override
   public ItemStack processAction3(int count3) {
      return count3 >= this.processAction4() ? null : this.itemStackArray[count3];
   }

   @Override
   public void processAction5() {
   }

   public ContainerCraftingIInventory(Container container2, int count3, int count4) {
      int count5 = count3 * count4;
      this.itemStackArray = new ItemStack[count5];
      this.container = container2;
      this.count = count3;
      this.count2 = count4;
   }
}
