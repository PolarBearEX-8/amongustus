package client.util;

import java.util.Collection;
import net.minecraft.client.resources.data.IMetadataSection;

public class AuxiliaryListenerAdapter7 implements IMetadataSection {
   public final Collection collection;

   public Collection getCollection() {
      return this.collection;
   }

   public AuxiliaryListenerAdapter7(Collection collection2) {
      this.collection = collection2;
   }
}
