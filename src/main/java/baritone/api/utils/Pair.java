package baritone.api.utils;

import java.util.Objects;

public final class Pair {
   private final Object first;
   private final Object second;

   public Pair(Object obj, Object obj2) {
      this.first = obj;
      this.second = obj2;
   }

   @Override
   public int hashCode() {
      return 31 * Objects.hashCode(this.first) + Objects.hashCode(this.second);
   }

   public Object getSecond() {
      return this.second;
   }

   public Object getFirst() {
      return this.first;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && obj.getClass() == Pair.class) {
         Pair pair = (Pair)obj;
         return Objects.equals(this.first, pair.first) && Objects.equals(this.second, pair.second);
      } else {
         return false;
      }
   }
}
