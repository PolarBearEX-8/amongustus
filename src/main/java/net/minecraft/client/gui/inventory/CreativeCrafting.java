package net.minecraft.client.gui.inventory;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CreativeCrafting implements ICrafting {
   private final Minecraft mc;

   public CreativeCrafting(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public void updateCraftingInventory(Container container, List list) {
   }

   @Override
   public void sendProgressBarUpdate(Container container, int n, int n2) {
   }

   @Override
   public void sendSlotContents(Container container, int n, ItemStack stack) {
      this.mc.playerController.sendSlotPacket(stack, n);
   }

   @Override
   public void sendAllWindowProperties(Container container, IInventory inventory) {
   }
}
