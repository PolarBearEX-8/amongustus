package client.util;

import client.setting.ExternalOptionType;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;

public final class InterpolationService extends ItemGroupManager {
   @Override
   public int getCount() {
      return ExternalOptionType.PAEONIA.getCount();
   }

   @Override
   public Item getApple() {
      return Item.processAction2(Blocks.blockDoublePlant);
   }

   public InterpolationService(int count, String text) {
      super(count, text);
   }
}
