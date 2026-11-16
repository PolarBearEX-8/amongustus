package cc.baritone.utils;

public final class ExceptionDecodingConThread extends Thread {
   public final ExceptionDecodingController exceptionDecodingController;
   public final int count;
   public final int count2;

   public ExceptionDecodingConThread(ExceptionDecodingController exceptionDecodingController2, int count3, int count4) {
      this.exceptionDecodingController = exceptionDecodingController2;
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public void run() {
      try {
         this.exceptionDecodingController.checkIndex(this.count, this.count2);
      } catch (Exception exception) {
         throw new RuntimeException(exception.getMessage());
      }
   }
}
