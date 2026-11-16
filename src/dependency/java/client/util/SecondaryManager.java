package client.util;

import client.setting.LocalOptionType;
import net.minecraft.block.BlockEntityTagManager;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SecondaryManager {
   public void applyExtendedManager(ExtendedManager extendedManager) {
      for (LocalOptionType localOptionType : LocalOptionType.values()) {
         extendedManager.resolveInternalListenerProvider(
            new ItemStack(Items.banner, 1, localOptionType.getCount2()),
            new Object[]{"###", "###", " | ", '#', new ItemStack(Blocks.wool, 1, localOptionType.getCount()), '|', Items.stick}
         );
      }

      extendedManager.add(new GeometryAggregator(null));
      extendedManager.add(new BlockEntityTagManager(null));
   }
}
