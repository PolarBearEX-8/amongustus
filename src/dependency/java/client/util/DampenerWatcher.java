package client.util;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMelonStem;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class DampenerWatcher extends DampenerEngine {
   public final CommonMapEntry commonMapEntry;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      LinkedHashMap map = Maps.newLinkedHashMap(blockState.getImmutableMap());
      if (blockState.getComparable(BlockMelonStem.autoWalkPath) != EnumFacing.UP) {
         map.remove(BlockMelonStem.auxiliaryImmutableSetEntry);
      }

      return new ResourceLocationAdapter((ResourceLocation)Block.iterableExtensionBridge.getObject2(blockState.getBlock()), this.resolveText(map));
   }

   public DampenerWatcher(CommonMapEntry commonMapEntry2) {
      this.commonMapEntry = commonMapEntry2;
   }
}
