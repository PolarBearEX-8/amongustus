package client.util;

import net.minecraft.util.RegistryNamespaced;
import org.apache.commons.lang3.Validate;

public class IterableExtensionBridge extends RegistryNamespaced {
   public final Object dataRef2;
   public Object dataRef;

   @Override
   public Object getObject3(int count) {
      Object object = super.getObject3(count);
      return object == null ? this.dataRef : object;
   }

   @Override
   public void applyObject2(int count, Object entry, Object entry2) {
      if (this.dataRef2.equals(entry)) {
         this.dataRef = entry2;
      }

      super.applyObject2(count, entry, entry2);
   }

   public void notNull() {
      Validate.notNull(this.dataRef2);
   }

   @Override
   public Object getObject(Object entry) {
      Object object = super.getObject(entry);
      return object == null ? this.dataRef : object;
   }

   public IterableExtensionBridge(Object entry) {
      this.dataRef2 = entry;
   }
}
