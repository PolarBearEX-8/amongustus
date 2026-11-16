package client.util;

public final class ThreadLocalAdapter extends ThreadLocal {
   public SmoothCollector getSmoothCollector() {
      return new SmoothCollector();
   }

   @Override
   public Object initialValue() {
      return this.getSmoothCollector();
   }
}
