package net.minecraft.block.properties;

import java.util.Collection;

public interface IProperty {
   Collection getAllowedValues();

   String getName();

   Class getValueClass();

   String getName(Comparable comparable);
}
