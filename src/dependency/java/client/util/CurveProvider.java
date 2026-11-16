package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CurveProvider extends Slot {
   public EntityPlayer entityPlayer;

   @Override
   public boolean isItemValid(ItemStack stack) {
      return matchesItemStack(stack);
   }

   public static boolean matchesItemStack(ItemStack stack) {
      return stack != null && (stack.getItem() == Items.potion || stack.getItem() == Items.glassBottle);
   }

   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public void processAction(EntityPlayer player, ItemStack stack) {
      if (stack.getItem() == Items.potion && stack.getIndexOffset() > 0) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter34);
      }

      super.processAction(player, stack);
   }

   public CurveProvider(EntityPlayer player, IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
      this.entityPlayer = player;
   }
}
