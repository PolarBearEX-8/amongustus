package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ItemSpade extends ItemTool {
   private static final Set EFFECTIVE_ON = Sets.newHashSet(
      new Block[]{
         Blocks.clay, Blocks.dirt, Blocks.farmland, Blocks.grass, Blocks.gravel, Blocks.mycelium, Blocks.sand, Blocks.snow, Blocks.snow_layer, Blocks.soul_sand
      }
   );

   @Override
   public boolean canHarvestBlock(Block block) {
      return block == Blocks.snow_layer ? true : block == Blocks.snow;
   }

   public ItemSpade(Item.ToolMaterial toolMaterial2) {
      super(1.0F, toolMaterial2, EFFECTIVE_ON);
   }
}
