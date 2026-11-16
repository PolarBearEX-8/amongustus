package client.util;

public class ClassHolder extends DampenerSorter {
   public Class memberRef;
   public int count2;
   public int count3;

   public ClassHolder(Class clazz, int count, int count4, int count5) {
      super(count);
      this.memberRef = clazz;
      this.count2 = count4;
      this.count3 = count5;
   }

   @Override
   public String toString() {
      return this.memberRef.getSimpleName() + "*(" + this.count2 + "-" + this.count3 + "):" + this.count;
   }
}
