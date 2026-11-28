package net.minecraft.item;

import net.minecraft.block.Block;

public class ItemCloth extends ItemBlock {
   @Override
   public String getUnlocalizedName(ItemStack stack) {
      return super.getUnlocalizedName() + "." + EnumDyeColor.byMetadata(stack.getMetadata()).getUnlocalizedName();
   }

   @Override
   public int getMetadata(int n) {
      return n;
   }

   public ItemCloth(Block block2) {
      super(block2);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }
}
