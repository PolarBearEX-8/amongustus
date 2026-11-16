package client.util;

import client.setting.IndirectOptionType;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;

public class DampenerPredictor extends DampenerEngine {
   public final CommonMapEntry commonMapEntry;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      LinkedHashMap map = Maps.newLinkedHashMap(blockState.getImmutableMap());
      String text = ImmutableSetWrapper.immutableSetEntry3.resolveText((Enum)((IndirectOptionType)map.remove(ImmutableSetWrapper.immutableSetEntry3)));
      map.remove(ImmutableSetWrapper.internalImmutableSetEntry);
      String text2 = ((Boolean)blockState.getComparable(ImmutableSetWrapper.internalImmutableSetEntry)) ? "all" : "normal";
      return new ResourceLocationAdapter(text + "_double_slab", text2);
   }

   public DampenerPredictor(CommonMapEntry commonMapEntry2) {
      this.commonMapEntry = commonMapEntry2;
   }
}
