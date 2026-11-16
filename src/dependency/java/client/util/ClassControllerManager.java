package client.util;

public class ClassControllerManager {
   public Class memberRef;
   public final int count;
   public int count2;
   public int count3;

   public boolean isActive() {
      return this.count3 == 0 || this.count2 < this.count3;
   }

   public boolean checkIndex(int count4) {
      return this.count3 == 0 || this.count2 < this.count3;
   }

   public ClassControllerManager(Class clazz, int count4, int count5) {
      this.memberRef = clazz;
      this.count = count4;
      this.count3 = count5;
   }
}
