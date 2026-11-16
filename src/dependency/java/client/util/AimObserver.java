package client.util;

public class AimObserver {
   public Object dataRef2;
   public Object dataRef;

   public Object getDataRef() {
      return this.dataRef;
   }

   public Object getDataRef2() {
      return this.dataRef2;
   }

   public AimObserver(Object entry, Object entry2) {
      this.dataRef2 = entry;
      this.dataRef = entry2;
   }
}
