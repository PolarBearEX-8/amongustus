package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ItemAxe extends ItemTool {
   private static final Set EFFECTIVE_ON = Sets.newHashSet(
      new Block[]{
         Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder
      }
   );

   public ItemAxe(Item.ToolMaterial toolMaterial2) {
      super(3.0F, toolMaterial2, EFFECTIVE_ON);
      this.attackCooldownTicks = 25;
   }

   @Override
   public float getStrVsBlock(ItemStack stack, Block block) {
      return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine
         ? super.getStrVsBlock(stack, block)
         : this.efficiencyOnProperMaterial;
   }
}
