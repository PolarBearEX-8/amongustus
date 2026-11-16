package cc.baritone.utils;

import java.io.PrintWriter;

public class FileReadThrowableService implements ThrowableService {
   public static final int count3 = 0;
   public static final int count4 = 1;
   public static final int count5 = 2;
   public static final int count6 = 7;
   public static final int count7 = 10;
   public PrintWriter printWriter;
   public int count8;

   public boolean checkIndex(int count) {
      return this.count8 >= count;
   }

   public FileReadThrowableService(PrintWriter printWriter2, int count) {
      this.printWriter = printWriter2;
      this.count8 = count;
   }

   @Override
   public void applyAStarServiceByAStarService(int count, AStarService aStarService) {
      if (count == 0 && this.checkIndex(2)) {
         String text = aStarService.toString();
         this.printWriter.println("File is a " + text);
      } else if (this.checkIndex(10)) {
         String text2 = aStarService.toString();
         this.printWriter.println("Read frame " + count + ": " + text2);
      }
   }

   @Override
   public boolean matchesThrowable(Throwable throwable) {
      if (this.count8 > 0) {
         throwable.printStackTrace(this.printWriter);
         this.printWriter.flush();
      }

      return false;
   }

   @Override
   public void applyIndex(int count, int count2, int count9) {
      int count10 = count9;
      if (this.checkIndex(2)) {
         switch (count) {
            case 2:
               if (count9 == 0) {
                  count10 = 1;
               }

               this.printWriter.println();
               this.printWriter.println("Converted " + count10 + " frames in " + count2 + " ms (" + count2 / count10 + " ms per frame.)");
         }
      }
   }

   public static FileReadThrowableService resolveFileReadThrowableService(int count) {
      return new FileReadThrowableService(new PrintWriter(System.out, true), count);
   }

   @Override
   public void applyAStarService(int count, AStarService aStarService) {
      if (count == 0 && this.checkIndex(2)) {
         String text = aStarService.toString();
         this.printWriter.println("File is a " + text);
      } else if (this.checkIndex(10)) {
         String text2 = aStarService.toString();
         this.printWriter.println("Prased frame " + count + ": " + text2);
      }
   }

   @Override
   public void applyAStarService(int count, AStarService aStarService, ExtraCustom extraCustom) {
      if (this.checkIndex(10)) {
         String text = aStarService.toString();
         this.printWriter.println("Decoded frame " + count + ": " + text);
         this.printWriter.println("Output: " + extraCustom);
      } else if (this.checkIndex(2)) {
         if (count == 0) {
            this.printWriter.print("Converting.");
            this.printWriter.flush();
         }

         if (count % 10 == 0) {
            this.printWriter.print('.');
            this.printWriter.flush();
         }
      }
   }
}
