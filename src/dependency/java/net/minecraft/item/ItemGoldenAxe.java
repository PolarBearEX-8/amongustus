package net.minecraft.item;

import client.setting.GlobalOptionType;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;

public class ItemGoldenAxe extends ItemTool {
   public static final Set set2 = Sets.newHashSet(
      new Block[]{
         Blocks.block142,
         Blocks.block8,
         Blocks.block140,
         Blocks.block104,
         Blocks.blockTrappedChest,
         Blocks.block146,
         Blocks.block120,
         Blocks.block67,
         Blocks.block81
      }
   );

   @Override
   public float processAction(ItemStack stack, Block block) {
      return block.getMaterial() != Material.material && block.getMaterial() != Material.material24 && block.getMaterial() != Material.material7
         ? super.processAction(stack, block)
         : this.factor;
   }

   public ItemGoldenAxe(GlobalOptionType globalOptionType) {
      super(3.0F, globalOptionType, set2);
      this.indexOffset = 25;
   }
}
