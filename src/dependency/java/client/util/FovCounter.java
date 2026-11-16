package client.util;

import client.event.CoreListener;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class FovCounter implements CoreListener {
   public final Minecraft mc;

   public FovCounter(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public void applyContainer(Container container, int count, ItemStack stack) {
      this.mc.playerController.applyItemStack(stack, count);
   }

   @Override
   public void applyContainer(Container container, List list) {
   }

   @Override
   public void applyContainer(Container container, IInventory iInventory) {
   }

   @Override
   public void applyContainer(Container container, int count, int count2) {
   }
}
