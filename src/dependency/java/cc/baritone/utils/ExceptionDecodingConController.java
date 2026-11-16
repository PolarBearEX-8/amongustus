package cc.baritone.utils;

public class ExceptionDecodingConController {
   public static int count = 1;
   public static int count2 = 2;
   public ExceptionDecodingController exceptionDecodingController;
   public int count3;
   public int count4;

   public void setCount4(int count5) {
      this.count4 = count5;
   }

   public int getCount4() {
      return this.count4;
   }

   public int getCount3() {
      return this.count3;
   }

   public void setCount3(int count5) {
      this.count3 = count5;
   }

   public ExceptionDecodingController getExceptionDecodingController() {
      return this.exceptionDecodingController;
   }

   public void setExceptionDecodingController(ExceptionDecodingController exceptionDecodingController2) {
      this.exceptionDecodingController = exceptionDecodingController2;
   }

   public ExceptionDecodingConController(ExceptionDecodingController exceptionDecodingController2, int count5, int count6) {
      this.count4 = count5;
      this.exceptionDecodingController = exceptionDecodingController2;
      this.count3 = count6;
   }
}
