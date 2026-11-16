package client.util;

import client.setting.MovementOffsetProperty;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import net.minecraft.block.BlockDirt;

public class DampenerProfiler extends DampenerEngine {
   public final CommonMapEntry commonMapEntry;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      LinkedHashMap map = Maps.newLinkedHashMap(blockState.getImmutableMap());
      String text = BlockDirt.immutableSetEntry.resolveText((Enum)((MovementOffsetProperty)map.remove(BlockDirt.immutableSetEntry)));
      if (MovementOffsetProperty.PODZOL != blockState.getComparable(BlockDirt.immutableSetEntry)) {
         map.remove(BlockDirt.internalImmutableSetEntry);
      }

      return new ResourceLocationAdapter(text, this.resolveText(map));
   }

   public DampenerProfiler(CommonMapEntry commonMapEntry2) {
      this.commonMapEntry = commonMapEntry2;
   }
}
