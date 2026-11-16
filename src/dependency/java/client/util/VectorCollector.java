package client.util;

public class VectorCollector {
   public Double memberRef;
   public Object dataRef;

   public VectorCollector(Double offset, Object entry) {
      this.memberRef = offset;
      this.dataRef = entry;
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public Double getMemberRef() {
      return this.memberRef;
   }
}
