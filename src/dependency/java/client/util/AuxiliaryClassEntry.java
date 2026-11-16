package client.util;

public class AuxiliaryClassEntry {
   public Class memberRef;
   public final int count;
   public int count2;
   public int count3;
   public boolean enabled;

   public boolean checkIndex(int count4) {
      return this.count3 == 0 || this.count2 < this.count3;
   }

   public AuxiliaryClassEntry(Class clazz, int count4, int count5, boolean flag) {
      this.memberRef = clazz;
      this.count = count4;
      this.count3 = count5;
      this.enabled = flag;
   }

   public AuxiliaryClassEntry(Class clazz, int count4, int count5) {
      this(clazz, count4, count5, false);
   }

   public boolean isActive() {
      return this.count3 == 0 || this.count2 < this.count3;
   }
}
