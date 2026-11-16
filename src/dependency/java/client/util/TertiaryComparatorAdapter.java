package client.util;

import java.util.Comparator;
import net.minecraft.block.BlockManager;
import net.minecraft.block.properties.IProperty;

public class TertiaryComparatorAdapter implements Comparator {
   public final BlockManager blockManager;

   public int computeCount(IProperty iProperty, IProperty iProperty2) {
      return iProperty.getLabelText().compareTo(iProperty2.getLabelText());
   }

   public TertiaryComparatorAdapter(BlockManager blockManager2) {
      this.blockManager = blockManager2;
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((IProperty)entry, (IProperty)entry2);
   }
}
