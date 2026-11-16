package client.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class DefaultClientManager {
   public void applyBlock(Block block, float factor) {
      GlStateManager.color4f2(factor, factor, factor, 1.0F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      DampenerCoordinator.dampenerCoordinator.applyItemStack(new ItemStack(block));
   }
}
