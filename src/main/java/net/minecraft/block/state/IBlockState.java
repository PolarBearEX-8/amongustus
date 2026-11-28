package net.minecraft.block.state;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;

public interface IBlockState {
   IBlockState withProperty(IProperty property, Comparable comparable);

   Block getBlock();

   boolean hasProperty(IProperty property);

   Collection getPropertyNames();

   ImmutableMap getProperties();

   IBlockState cycleProperty(IProperty property);

   Comparable getValue(IProperty property);
}
