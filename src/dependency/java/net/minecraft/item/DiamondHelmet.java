package net.minecraft.item;

import client.setting.LocalOptionType;
import net.minecraft.block.Block;

public class DiamondHelmet extends ItemBlock {
   @Override
   public int processAction2(int count) {
      return count;
   }

   @Override
   public String getText(ItemStack stack) {
      return super.getString() + "." + LocalOptionType.resolveLocalOptionType(stack.getIndexOffset()).getLabelText();
   }

   public DiamondHelmet(Block block) {
      super(block);
      this.processAction3(0);
      this.processAction(true);
   }
}
