package net.minecraft.item;

import net.minecraft.block.Block;

public class ItemColored extends ItemBlock {
   private final Block coloredBlock;
   private String[] subtypeNames;

   public ItemColored(Block block2, boolean flag) {
      super(block2);
      this.coloredBlock = block2;
      if (flag) {
         this.setMaxDamage(0);
         this.setHasSubtypes(true);
      }
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      return this.coloredBlock.getRenderColor(this.coloredBlock.getStateFromMeta(stack.getMetadata()));
   }

   public ItemColored setSubtypeNames(String[] strings) {
      this.subtypeNames = strings;
      return this;
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      if (this.subtypeNames == null) {
         return super.getUnlocalizedName(stack);
      } else {
         int metadata = stack.getMetadata();
         return metadata >= 0 && metadata < this.subtypeNames.length ? super.getUnlocalizedName(stack) + "." + this.subtypeNames[metadata] : super.getUnlocalizedName(stack);
      }
   }

   @Override
   public int getMetadata(int n) {
      return n;
   }
}
