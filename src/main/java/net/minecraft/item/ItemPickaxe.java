package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ItemPickaxe extends ItemTool {
   private static final Set EFFECTIVE_ON = Sets.newHashSet(
      new Block[]{
         Blocks.activator_rail,
         Blocks.coal_ore,
         Blocks.cobblestone,
         Blocks.detector_rail,
         Blocks.diamond_block,
         Blocks.diamond_ore,
         Blocks.double_stone_slab,
         Blocks.golden_rail,
         Blocks.gold_block,
         Blocks.gold_ore,
         Blocks.ice,
         Blocks.iron_block,
         Blocks.iron_ore,
         Blocks.lapis_block,
         Blocks.lapis_ore,
         Blocks.lit_redstone_ore,
         Blocks.mossy_cobblestone,
         Blocks.netherrack,
         Blocks.packed_ice,
         Blocks.rail,
         Blocks.redstone_ore,
         Blocks.sandstone,
         Blocks.red_sandstone,
         Blocks.stone,
         Blocks.stone_slab
      }
   );

   @Override
   public boolean canHarvestBlock(Block block) {
      return block == Blocks.obsidian
         ? this.toolMaterial.getHarvestLevel() == 3
         : (
            block == Blocks.diamond_block || block == Blocks.diamond_ore
               ? this.toolMaterial.getHarvestLevel() >= 2
               : (
                  block == Blocks.emerald_ore || block == Blocks.emerald_block
                     ? this.toolMaterial.getHarvestLevel() >= 2
                     : (
                        block == Blocks.gold_block || block == Blocks.gold_ore
                           ? this.toolMaterial.getHarvestLevel() >= 2
                           : (
                              block == Blocks.iron_block || block == Blocks.iron_ore
                                 ? this.toolMaterial.getHarvestLevel() >= 1
                                 : (
                                    block == Blocks.lapis_block || block == Blocks.lapis_ore
                                       ? this.toolMaterial.getHarvestLevel() >= 1
                                       : (
                                          block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore
                                             ? (
                                                block.getMaterial() == Material.rock
                                                   ? true
                                                   : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)
                                             )
                                             : this.toolMaterial.getHarvestLevel() >= 2
                                       )
                                 )
                           )
                     )
               )
         );
   }

   @Override
   public float getStrVsBlock(ItemStack stack, Block block) {
      return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock
         ? super.getStrVsBlock(stack, block)
         : this.efficiencyOnProperMaterial;
   }

   public ItemPickaxe(Item.ToolMaterial toolMaterial2) {
      super(2.0F, toolMaterial2, EFFECTIVE_ON);
      this.attackCooldownTicks = 16;
   }
}
