package net.minecraft.item;

import net.minecraft.block.BlockDoublePlant;
import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerGrass;

public class ItemDoublePlant extends ItemMultiTexture {
   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      BlockDoublePlant.EnumPlantType enumPlantType = BlockDoublePlant.EnumPlantType.byMetadata(stack.getMetadata());
      return enumPlantType != BlockDoublePlant.EnumPlantType.GRASS && enumPlantType != BlockDoublePlant.EnumPlantType.FERN
         ? super.getColorFromItemStack(stack, n)
         : ColorizerGrass.getGrassColor(0.5, 1.0);
   }

   public ItemDoublePlant(Block block2, Block block3, Function function) {
      super(block2, block3, function);
   }
}
