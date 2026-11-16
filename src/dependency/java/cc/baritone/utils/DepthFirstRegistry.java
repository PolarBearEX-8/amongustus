package cc.baritone.utils;

import java.util.Objects;

public final class DepthFirstRegistry {
   public final Object dataRef;
   public final Object dataRef2;

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && other.getClass() == DepthFirstRegistry.class) {
         DepthFirstRegistry depthFirstRegistry = (DepthFirstRegistry)other;
         return Objects.equals(this.dataRef, depthFirstRegistry.dataRef) && Objects.equals(this.dataRef2, depthFirstRegistry.dataRef2);
      } else {
         return false;
      }
   }

   public DepthFirstRegistry(Object entry, Object entry2) {
      this.dataRef = entry;
      this.dataRef2 = entry2;
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   @Override
   public int hashCode() {
      return 31 * Objects.hashCode(this.dataRef) + Objects.hashCode(this.dataRef2);
   }

   public Object getDataRef2() {
      return this.dataRef2;
   }
}
