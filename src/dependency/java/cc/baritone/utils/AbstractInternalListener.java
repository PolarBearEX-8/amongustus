package cc.baritone.utils;

public abstract class AbstractInternalListener implements InternalListener {
   public int count;
   public int count2;
   public int count3;

   @Override
   public int getCount2() {
      return this.count2;
   }

   @Override
   public int getCount3() {
      return this.count3;
   }

   @Override
   public int getCount() {
      return this.count;
   }

   public AbstractInternalListener(int count4, int count5, int count6) {
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   public AbstractInternalListener() {
      this(0, 0, 0);
   }
}
