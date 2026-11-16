package client.util;

import com.google.common.base.Function;
import net.minecraft.block.properties.IProperty;

public final class AuxiliaryNULLFunction implements Function {
   public String resolveText(IProperty iProperty) {
      return iProperty == null ? "<NULL>" : iProperty.getLabelText();
   }

   public Object apply(Object entry) {
      return this.resolveText((IProperty)entry);
   }
}
