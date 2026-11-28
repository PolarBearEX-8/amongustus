package net.minecraft.dispenser;

import net.minecraft.item.ItemStack;

public interface IBehaviorDispenseItem {
   IBehaviorDispenseItem itemDispenseBehaviorProvider = new IBehaviorDispenseItem.DefaultBehaviorDispenseItem();

   ItemStack dispense(IBlockSource blockSource, ItemStack stack);


   public static final class DefaultBehaviorDispenseItem implements IBehaviorDispenseItem {
      @Override
      public ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
         return stack;
      }
   }
}
