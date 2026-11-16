package net.minecraft.item;

public class ItemBook extends Item {
   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return stack.animationsToGo == 1;
   }
}
