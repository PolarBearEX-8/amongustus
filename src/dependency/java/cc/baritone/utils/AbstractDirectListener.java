package cc.baritone.utils;

public abstract class AbstractDirectListener implements DirectListener {
   public final int count;
   public final int count2;
   public final int count3;

   @Override
   public int getCount() {
      return this.count;
   }

   @Override
   public int getCount3() {
      return this.count3;
   }

   public AbstractDirectListener(int count4, int count5, int count6) {
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   @Override
   public int getCount2() {
      return this.count2;
   }
}
