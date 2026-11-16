package net.minecraft.crash;

import client.util.IBlockState;
import client.util.IBlockStateTask;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTask;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPosTask;
import net.minecraft.util.BlockPosTaskImpl;

public class CrashReportCategory {
   public final CrashReport crashReport;
   public final String labelText;
   public final List list = Lists.newArrayList();
   public StackTraceElement[] stackTraceElementArray = new StackTraceElement[0];

   public static void applyCrashReportCategory(CrashReportCategory crashReportCategory, BlockPos blockPos, Block block, int count) {
      int idFromBlock = Block.getIdFromBlock(block);
      crashReportCategory.applyText("Block type", new BlockTask(idFromBlock, block));
      crashReportCategory.applyText("Block data value", new GotTask(count));
      crashReportCategory.applyText("Block location", new BlockPosTaskImpl(blockPos));
   }

   public void applyText(String text, Callable callable) {
      try {
         this.add(text, callable.call());
      } catch (Throwable throwable) {
         this.applyText(text, throwable);
      }
   }

   public CrashReportCategory(CrashReport crashReport2, String text) {
      this.crashReport = crashReport2;
      this.labelText = text;
   }

   public boolean matchesStackTraceElement(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
      if (this.stackTraceElementArray.length != 0 && stackTraceElement != null) {
         StackTraceElement stackTraceElement3 = this.stackTraceElementArray[0];
         if (stackTraceElement3.isNativeMethod() == stackTraceElement.isNativeMethod()
            && stackTraceElement3.getClassName().equals(stackTraceElement.getClassName())
            && java.util.Objects.equals(stackTraceElement3.getFileName(), stackTraceElement.getFileName())
            && stackTraceElement3.getMethodName().equals(stackTraceElement.getMethodName())) {
            if (stackTraceElement2 != null != this.stackTraceElementArray.length > 1) {
               return false;
            } else if (stackTraceElement2 != null && !this.stackTraceElementArray[1].equals(stackTraceElement2)) {
               return false;
            } else {
               this.stackTraceElementArray[0] = stackTraceElement;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public int computeCount(int count) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      if (stackTrace.length <= 0) {
         return 0;
      } else {
         this.stackTraceElementArray = new StackTraceElement[stackTrace.length - 3 - count];
         System.arraycopy(stackTrace, 3 + count, this.stackTraceElementArray, 0, this.stackTraceElementArray.length);
         return this.stackTraceElementArray.length;
      }
   }

   public static String resolveText(double posX, double posY, double posZ) {
      return String.format("%.2f,%.2f,%.2f - %s", posX, posY, posZ, resolveText(new BlockPos(posX, posY, posZ)));
   }

   public static void applyCrashReportCategory(CrashReportCategory crashReportCategory, BlockPos blockPos, IBlockState blockState) {
      crashReportCategory.applyText("Block", new IBlockStateTask(blockState));
      crashReportCategory.applyText("Block location", new BlockPosTask(blockPos));
   }

   public void applyStringBuilder(StringBuilder builder) {
      builder.append("-- ").append(this.labelText).append(" --\n");
      builder.append("Details:");

      for (Controller controller : (Iterable<Controller>) (Iterable) (this.list)) {
         builder.append("\n\t");
         builder.append(controller.getLabelText());
         builder.append(": ");
         builder.append(controller.getLabelText2());
      }

      if (this.stackTraceElementArray != null && this.stackTraceElementArray.length > 0) {
         builder.append("\nStacktrace:");

         for (StackTraceElement stackTraceElement : this.stackTraceElementArray) {
            builder.append("\n\tat ");
            builder.append(stackTraceElement.toString());
         }
      }
   }

   public void applyIndex(int count) {
      StackTraceElement[] stackTraceElementArray2 = new StackTraceElement[this.stackTraceElementArray.length - count];
      System.arraycopy(this.stackTraceElementArray, 0, stackTraceElementArray2, 0, stackTraceElementArray2.length);
      this.stackTraceElementArray = stackTraceElementArray2;
   }

   public StackTraceElement[] getStackTraceElementArray() {
      return this.stackTraceElementArray;
   }

   public static String resolveText(BlockPos blockPos) {
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      StringBuilder builder = new StringBuilder();

      try {
         builder.append(String.format("World: (%d,%d,%d)", coordX, coordY, coordZ));
      } catch (Throwable throwable) {
         builder.append("(Error finding world loc)");
      }

      builder.append(", ");

      try {
         int count = coordX >> 4;
         int count2 = coordZ >> 4;
         int count3 = coordX & 15;
         int count4 = coordY >> 4;
         int count5 = coordZ & 15;
         int count6 = count << 4;
         int count7 = count2 << 4;
         int count8 = (count + 1 << 4) - 1;
         int count9 = (count2 + 1 << 4) - 1;
         builder.append(
            String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", count3, count4, count5, count, count2, count6, count7, count8, count9)
         );
      } catch (Throwable throwable2) {
         builder.append("(Error finding chunk loc)");
      }

      builder.append(", ");

      try {
         int count10 = coordX >> 9;
         int count11 = coordZ >> 9;
         int count12 = count10 << 5;
         int count13 = count11 << 5;
         int count14 = (count10 + 1 << 5) - 1;
         int count15 = (count11 + 1 << 5) - 1;
         int count16 = count10 << 9;
         int count17 = count11 << 9;
         int count18 = (count10 + 1 << 9) - 1;
         int count19 = (count11 + 1 << 9) - 1;
         builder.append(
            String.format(
               "Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)",
               count10,
               count11,
               count12,
               count13,
               count14,
               count15,
               count16,
               count17,
               count18,
               count19
            )
         );
      } catch (Throwable throwable3) {
         builder.append("(Error finding world loc)");
      }

      return builder.toString();
   }

   public void add(String text, Object entry) {
      this.list.add(new Controller(text, entry));
   }

   public void applyText(String text, Throwable throwable) {
      this.add(text, throwable);
   }
}
