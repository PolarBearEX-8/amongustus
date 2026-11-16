package net.minecraft.block;

import client.setting.ExternalOptionType;
import client.util.DefaultIntArrayEntry;
import com.google.common.base.Function;
import net.minecraft.item.ItemStack;

public class BlockEntryAdapter extends BlockEntry {
   @Override
   public int processAction(ItemStack stack, int count) {
      ExternalOptionType externalOptionType = ExternalOptionType.resolveExternalOptionType(stack.getIndexOffset());
      return externalOptionType != ExternalOptionType.GRASS && externalOptionType != ExternalOptionType.FERN
         ? super.processAction(stack, count)
         : DefaultIntArrayEntry.computeCount(0.5, 1.0);
   }

   public BlockEntryAdapter(Block block, Block block2, Function mapper) {
      super(block, block2, mapper);
   }
}
