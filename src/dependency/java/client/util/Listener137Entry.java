package client.util;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public class Listener137Entry extends DampenerEngine {
   public final IProperty iProperty;
   public final String labelText;
   public final List list;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      LinkedHashMap map = Maps.newLinkedHashMap(blockState.getImmutableMap());
      String text;
      if (this.iProperty == null) {
         text = ((ResourceLocation)Block.iterableExtensionBridge.getObject2(blockState.getBlock())).toString();
      } else {
         text = this.iProperty.resolveText((Comparable)map.remove(this.iProperty));
      }

      if (this.labelText != null) {
         text = text + this.labelText;
      }

      for (IProperty iProperty2 : (Iterable<IProperty>)(Iterable<?>)this.list) {
         map.remove(iProperty2);
      }

      return new ResourceLocationAdapter(text, this.resolveText(map));
   }

   public Listener137Entry(IProperty iProperty2, String text, List list2, DistanceProvider distanceProvider) {
      this(iProperty2, text, list2);
   }

   private Listener137Entry(IProperty iProperty2, String text, List list2) {
      this.iProperty = iProperty2;
      this.labelText = text;
      this.list = list2;
   }
}
