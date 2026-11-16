package client.util;

import client.setting.CoreOptionType;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;

public class DampenerDirector extends DampenerEngine {
   public final CommonMapEntry commonMapEntry;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      LinkedHashMap map = Maps.newLinkedHashMap(blockState.getImmutableMap());
      String text = ImmutableSetHolder.immutableSetEntry3.resolveText((Enum)((CoreOptionType)map.remove(ImmutableSetHolder.immutableSetEntry3)));
      map.remove(ImmutableSetWrapper.internalImmutableSetEntry);
      String text2 = ((Boolean)blockState.getComparable(ImmutableSetHolder.internalImmutableSetEntry)) ? "all" : "normal";
      return new ResourceLocationAdapter(text + "_double_slab", text2);
   }

   public DampenerDirector(CommonMapEntry commonMapEntry2) {
      this.commonMapEntry = commonMapEntry2;
   }
}
