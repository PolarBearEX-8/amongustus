package net.minecraft.item;

import client.setting.GlobalOptionType;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;

public class ItemGoldenPickaxe extends ItemTool {
   public static final Set set2 = Sets.newHashSet(
      new Block[]{
         Blocks.block106,
         Blocks.block89,
         Blocks.block65,
         Blocks.block40,
         Blocks.block91,
         Blocks.block44,
         Blocks.extraBlockAdapter5,
         Blocks.block50,
         Blocks.block35,
         Blocks.block121,
         Blocks.block53,
         Blocks.block16,
         Blocks.block103,
         Blocks.block76,
         Blocks.block47,
         Blocks.block,
         Blocks.block131,
         Blocks.block96,
         Blocks.block105,
         Blocks.block13,
         Blocks.block25,
         Blocks.block136,
         Blocks.block119,
         Blocks.stone,
         Blocks.extraBlockAdapter6
      }
   );

   @Override
   public float processAction(ItemStack stack, Block block) {
      return block.getMaterial() != Material.material5 && block.getMaterial() != Material.material31 && block.getMaterial() != Material.material4
         ? super.processAction(stack, block)
         : this.factor;
   }

   @Override
   public boolean processAction(Block block) {
      return block == Blocks.block113
         ? this.globalOptionType.getCount() == 3
         : (
            block == Blocks.block91 || block == Blocks.block44
               ? this.globalOptionType.getCount() >= 2
               : (
                  block == Blocks.block70 || block == Blocks.block93
                     ? this.globalOptionType.getCount() >= 2
                     : (
                        block == Blocks.block35 || block == Blocks.block121
                           ? this.globalOptionType.getCount() >= 2
                           : (
                              block == Blocks.block16 || block == Blocks.block103
                                 ? this.globalOptionType.getCount() >= 1
                                 : (
                                    block == Blocks.block76 || block == Blocks.block47
                                       ? this.globalOptionType.getCount() >= 1
                                       : (
                                          block != Blocks.block25 && block != Blocks.block
                                             ? (
                                                block.getMaterial() == Material.material4
                                                   ? true
                                                   : (block.getMaterial() == Material.material5 ? true : block.getMaterial() == Material.material31)
                                             )
                                             : this.globalOptionType.getCount() >= 2
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public ItemGoldenPickaxe(GlobalOptionType globalOptionType) {
      super(2.0F, globalOptionType, set2);
      this.indexOffset = 16;
   }
}
