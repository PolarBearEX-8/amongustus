package net.minecraft.block.properties;

import java.util.Collection;

public interface IProperty {
   String getLabelText();

   Collection getCollection();

   String resolveText(Comparable comparable);

   Class getMemberRef();
}
