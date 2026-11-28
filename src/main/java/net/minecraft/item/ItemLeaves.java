package net.minecraft.item;

import net.minecraft.block.BlockLeaves;

public class ItemLeaves extends ItemBlock {
   private final BlockLeaves leaves;

   @Override
   public int getMetadata(int n) {
      return n | 4;
   }

   public ItemLeaves(BlockLeaves blockLeaves) {
      super(blockLeaves);
      this.leaves = blockLeaves;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      return this.leaves.getRenderColor(this.leaves.getStateFromMeta(stack.getMetadata()));
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      return super.getUnlocalizedName() + "." + this.leaves.getWoodType(stack.getMetadata()).getUnlocalizedName();
   }
}
