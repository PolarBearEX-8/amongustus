package client.util;

public class Pair {
   public final Object first;
   public final Object second;

   public Object getSecond() {
      return this.second;
   }

   public Object getFirst() {
      return this.first;
   }

   public Pair(Object entry, Object entry2) {
      this.second = entry;
      this.first = entry2;
   }
}
