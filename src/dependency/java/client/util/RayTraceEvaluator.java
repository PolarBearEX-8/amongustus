package client.util;

public class RayTraceEvaluator {
   public int count2;
   public int count;

   public synchronized void updateState() {
      this.count = this.count2;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public synchronized int getCount() {
      return this.count++;
   }

   public RayTraceEvaluator(int count3) {
      this.count2 = count3;
      this.count = count3;
   }
}
