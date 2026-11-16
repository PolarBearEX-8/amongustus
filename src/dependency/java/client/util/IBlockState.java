package client.util;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;

public interface IBlockState {
   boolean containsKey(IProperty iProperty);

   Collection getCollection();

   IBlockState resolveIBlockState(IProperty iProperty, Comparable comparable);

   Block getBlock();

   Comparable getComparable(IProperty iProperty);

   ImmutableMap getImmutableMap();

   IBlockState resolveIBlockState(IProperty iProperty);
}
