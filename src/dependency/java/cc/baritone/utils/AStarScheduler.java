package cc.baritone.utils;

public class AStarScheduler extends AuxiliaryExceptionAdapter implements ServiceAdapterImpl {
   public int count10 = 256;

   public AStarScheduler(String text, Throwable throwable) {
      super(text, throwable);
   }

   public int getCount10() {
      return this.count10;
   }

   public AStarScheduler(int count, Throwable throwable) {
      this(resolveText(count), throwable);
      this.count10 = count;
   }

   public static String resolveText(int count) {
      return "Bitstream errorcode " + Integer.toHexString(count);
   }
}
