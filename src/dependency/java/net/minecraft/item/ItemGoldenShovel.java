package net.minecraft.item;

import client.setting.GlobalOptionType;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ItemGoldenShovel extends ItemTool {
   public static final Set set2 = Sets.newHashSet(
      new Block[]{
         Blocks.block123,
         Blocks.block115,
         Blocks.block7,
         Blocks.blockGrass,
         Blocks.block45,
         Blocks.blockMycelium,
         Blocks.blockSand,
         Blocks.block61,
         Blocks.block74,
         Blocks.block126
      }
   );

   @Override
   public boolean processAction(Block block) {
      return block == Blocks.block74 ? true : block == Blocks.block61;
   }

   public ItemGoldenShovel(GlobalOptionType globalOptionType) {
      super(1.0F, globalOptionType, set2);
   }
}
